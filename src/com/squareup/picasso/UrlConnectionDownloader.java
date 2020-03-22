/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.squareup.picasso;

import java.io.IOException;
import android.net.http.HttpResponseCache;
import android.content.Context;
import java.io.File;
import android.net.Uri;
import android.os.Build;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionDownloader implements Downloader {
    private static final String FORCE_CACHE = "only-if-cached,max-age=2147483647";
    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private final Context context;
    private static final Object lock = localUrlConnectionDownloader.11;
    localUrlConnectionDownloader.11.super();
    private static final ThreadLocal<StringBuilder> CACHE_HEADER_BUILDER = new ThreadLocal() {
        
        protected StringBuilder initialValue() {
            return ();
        }
    };
    
    public UrlConnectionDownloader(Context p1) {
        context = p1.getApplicationContext();
    }
    
    protected HttpURLConnection openConnection(Uri p1) throws IOException {
        localURL1 = (HttpURLConnection)new URL(p1.toString()).openConnection();
        new URL(p1.toString()).setConnectTimeout(0x3a98);
        new URL(p1.toString()).setReadTimeout(0x4e20);
        return new URL(p1.toString());
    }
    
    public Downloader.Response load(Uri p1, int p2) throws IOException {
        if(Build.VERSION.SDK_INT >= 0xe) {
            installCacheIfNeeded(context);
        }
        localInputStream1 = openConnection(p1);
        0xe.setUseCaches(true);
        if(p2 != 0) {
            if(NetworkPolicy.isOfflineOnly(p2)) {
                String localString2 = "only-if-cached,max-age=2147483647";
            } else {
                CACHE_HEADER_BUILDER = (StringBuilder)CACHE_HEADER_BUILDER.get();
                CACHE_HEADER_BUILDER.setLength(0x0);
                if(!NetworkPolicy.shouldReadFromDiskCache(p2)) {
                    CACHE_HEADER_BUILDER.append("no-cache");
                } else if(!NetworkPolicy.shouldWriteToDiskCache(p2)) {
                    if(CACHE_HEADER_BUILDER.length() > 0) {
                        CACHE_HEADER_BUILDER.append(0x2c);
                    } else {
                        CACHE_HEADER_BUILDER.append("no-store");
                    } else {
                        CACHE_HEADER_BUILDER = CACHE_HEADER_BUILDER.toString();
                    }
                }
            }
            0xe.setRequestProperty("Cache-Control", NetworkPolicy.isOfflineOnly(p2));
        }
        localString2 = 0xe.getResponseCode();
        if(0x1 >= 0x12c) {
            0xe.disconnect();
            throw new Downloader.ResponseException(0x1 + " " + 0xe.getResponseMessage(), p2, 0x1);
        }
        -0x1 = (long)0xe.getHeaderFieldInt("Content-Length", -0x1);
        localString2 = Utils.parseResponseSourceHeader(0xe.getHeaderField("X-Android-Response-Source"));
        return new Downloader.Response(0xe.getInputStream(), true, locallong3);
    }
    
    public void shutdown() {
        if((Build.VERSION.SDK_INT >= 0xe) && (cache != null)) {
            UrlConnectionDownloader.ResponseCacheIcs.close(cache);
        }
    }
    
    private static void installCacheIfNeeded(Context p1) {
        if(cache == null) {
            try {
                synchronized(lock) {
                    if(cache == null) {
                        cache = UrlConnectionDownloader.ResponseCacheIcs.install(p1);
                    }
                }
                return;
            }
        } catch(IOException localIOException1) {
        }
    }
    
    class ResponseCacheIcs {
        
        static Object install(Context p1) throws IOException {
            // :( Parsing error. Please contact me.
        }
        
        static void close(Object p1) {
            try {
                close();
                return;
            } catch(IOException localIOException1) {
            }
        }
    }
}
