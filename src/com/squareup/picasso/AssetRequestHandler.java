/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.squareup.picasso;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;

class AssetRequestHandler extends RequestHandler {
    protected static final String ANDROID_ASSET = "android_asset";
    private final AssetManager assetManager;
    private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    
    public AssetRequestHandler(Context p1) {
        assetManager = p1.getAssets();
    }
    
    public boolean canHandleRequest(Request p1) {
        // :( Parsing error. Please contact me.
    }
    
    public RequestHandler.Result load(Request p1, int p2) throws IOException {
        assetManager = assetManager.open(getFilePath(p1));
        return new RequestHandler.Result(assetManager, Picasso.LoadedFrom.DISK);
    }
    
    static String getFilePath(Request p1) {
        return uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}