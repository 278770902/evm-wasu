(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{s07Y:function(t,e,n){"use strict";n.r(e),n.d(e,"createSwipeBackGesture",function(){return a});var r=n("AXJG"),o=n("kzDy");const a=(t,e,n,a,c)=>{const s=t.ownerDocument.defaultView;return Object(o.createGesture)({el:t,gestureName:"goback-swipe",gesturePriority:40,threshold:10,canStart:t=>t.startX<=50&&e(),onStart:n,onMove:t=>{a(t.deltaX/s.innerWidth)},onEnd:t=>{const e=s.innerWidth,n=t.deltaX/e,o=t.velocityX,a=o>=0&&(o>.2||t.deltaX>e/2),i=(a?1-n:n)*e;let u=0;if(i>5){const t=i/Math.abs(o);u=Math.min(t,540)}c(a,n<=0?.01:Object(r.c)(0,n,.9999),u)}})}}}]);