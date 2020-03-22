/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.squareup.picasso;

import android.graphics.Bitmap;

class FetchAction extends Action {
    private Callback callback;
    private final Object target;
    
    FetchAction(Picasso p1, Request p2, int p3, int p4, Object p5, String p6, Callback p7) {
        super(p1, 0x0, p2, p3, p4, 0x0, 0x0, p6, p5, false);
        target = new Object();
        callback = p7;
    }
    
    void complete(Bitmap p1, Picasso.LoadedFrom p2) {
        if(callback != null) {
            callback.onSuccess();
        }
    }
    
    void error() {
        if(callback != null) {
            callback.onError();
        }
    }
    
    void cancel() {
        super.cancel();
        callback = 0x0;
    }
    
    Object getTarget() {
        return target;
    }
}
