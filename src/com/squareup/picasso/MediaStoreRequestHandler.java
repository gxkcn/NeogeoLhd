/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.squareup.picasso;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import java.io.IOException;

class MediaStoreRequestHandler extends ContentStreamRequestHandler {
    private static final String[] CONTENT_ORIENTATION = new String[] {"orientation"}
    
    MediaStoreRequestHandler(Context p1) {
        super(p1);
    }
    
    public boolean canHandleRequest(Request p1) {
        // :( Parsing error. Please contact me.
    }
    
    public RequestHandler.Result load(Request p1, int p2) throws IOException {
        // :( Parsing error. Please contact me.
    }
    
    static MediaStoreRequestHandler.PicassoKind getPicassoKind(int p1, int p2) {
        if((p1 <= MediaStoreRequestHandler.PicassoKind.MICRO.width) && (p2 <= MediaStoreRequestHandler.PicassoKind.MICRO.height)) {
            return MediaStoreRequestHandler.PicassoKind.MICRO;
        }
        if((p1 <= MediaStoreRequestHandler.PicassoKind.MINI.width) && (p2 <= MediaStoreRequestHandler.PicassoKind.MINI.height)) {
            return MediaStoreRequestHandler.PicassoKind.MINI;
        }
        return MediaStoreRequestHandler.PicassoKind.FULL;
    }
    
    static int getExifOrientation(ContentResolver p1, Uri p2) {
        // :( Parsing error. Please contact me.
    }
}
