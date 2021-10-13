package com.evmtv.cloudvideo.common.utils.thread;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.utils.view.EvmProgress;
import com.evmtv.cloudvideo.common.view.dialog.ProgressDialog;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class AppExecutors {
    private static final String TAG = "AppExecutors";
    /**
     * 磁盘IO线程池
     **/
    private final ExecutorService diskIO;
    /**
     * 网络IO线程池
     **/
    private final ExecutorService networkIO;
    /**
     * UI线程
     **/
    private final Executor mainThread;
    /**
     * 定时任务线程池
     **/
    private final ScheduledExecutorService scheduledExecutor;

    private volatile static AppExecutors appExecutors;

    public static AppExecutors getInstance() {
        if (appExecutors == null) {
            synchronized (AppExecutors.class) {
                if (appExecutors == null) {
                    appExecutors = new AppExecutors();
                }
            }
        }
        return appExecutors;
    }

    public AppExecutors(ExecutorService diskIO, ExecutorService networkIO, Executor mainThread, ScheduledExecutorService scheduledExecutor) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
        this.scheduledExecutor = scheduledExecutor;
    }

    public AppExecutors() {
        this(diskIoExecutor(), networkExecutor(), new MainThreadExecutor(), scheduledThreadPoolExecutor());
    }

    /**
     * 定时(延时)任务线程池
     * <p>
     * 替代Timer,执行定时任务,延时任务
     */
    public ScheduledExecutorService scheduledExecutor() {
        return scheduledExecutor;
    }

    /**
     * 磁盘IO线程池（单线程）
     * <p>
     * 和磁盘操作有关的进行使用此线程(如读写数据库,读写文件)
     * 禁止延迟,避免等待
     * 此线程不用考虑同步问题
     */
    public ExecutorService diskIO() {
        return diskIO;
    }

    /**
     * 网络IO线程池
     * <p>
     * 网络请求,异步任务等适用此线程
     * 不建议在这个线程 sleep 或者 wait
     */
    public ExecutorService networkIO() {
        return networkIO;
    }


    public void networkIOToMain(OrderMethodInter methodInter) {
        networkIOToMain(null, methodInter);
    }


    public void networkIOToMain(Context context, OrderMethodInter methodInter) {
        ProgressDialog dialog = null;
        if (context != null) {
            if (dialog == null) {
                dialog = new ProgressDialog(context);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            dialog.show();
        }
        ProgressDialog finalDialog = dialog;
        networkIO.execute(new Runnable() {
            @Override
            public void run() {
                methodInter.IO();
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (finalDialog != null){
                            finalDialog.hide();
                            finalDialog.dismiss();
                        }
                        methodInter.Main();
                    }
                });
            }
        });
    }

    /**
     * UI线程
     * <p>
     * Android 的MainThread
     * UI线程不能做的事情这个都不能做
     */
    public Executor mainThread() {
        return mainThread;
    }


    private static ScheduledExecutorService scheduledThreadPoolExecutor() {
//        return new ScheduledThreadPoolExecutor(16, r -> new Thread(r, "scheduled_executor"), (r, executor) -> Log.e(TAG, "rejectedExecution: scheduled executor queue overflow"));
        return new ScheduledThreadPoolExecutor(16, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "scheduled_executor");
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Log.e(TAG, "rejectedExecution: scheduled executor queue overflow");
            }
        });
    }

    private static ExecutorService diskIoExecutor() {
//        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS
//                , new LinkedBlockingQueue<>(1024)
//                , r -> new Thread(r, "disk_executor")
//                , (r, executor) -> Log.e(TAG, "rejectedExecution: disk io executor queue overflow"));
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS
                , new LinkedBlockingQueue<Runnable>(1024), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "disk_executor");
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Log.e(TAG, "rejectedExecution: disk io executor queue overflow");
            }
        });
    }

    private static ExecutorService networkExecutor() {
//        return new ThreadPoolExecutor(3, 6, 1000,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(6)
//                , r -> new Thread(r, "network_executor")
//                , (r, executor) -> Log.e(TAG, "rejectedExecution: network executor queue overflow"));
        return new ThreadPoolExecutor(3, 6, 1000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(6)
                , new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "network_executor");
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Log.e(TAG, "rejectedExecution: network executor queue overflow");
            }
        });
    }


    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
