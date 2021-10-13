package com.evmtv.cloudvideo.common.utils.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int type;

    public SpacesItemDecoration(int space, int type) {
        this.space = space;
        this.type = type;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
       /* outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.top = space;
        */

        switch (type) {
            case 0:
                if (parent.getChildPosition(view) == 0)
                    outRect.left = 0;
                else
                    outRect.left = space;
                break;
            case 1:
                outRect.left = space;
                if (parent.getChildPosition(view) != 0)
                    outRect.top = space;
                break;
        }
        outRect.right = space;
    }
}