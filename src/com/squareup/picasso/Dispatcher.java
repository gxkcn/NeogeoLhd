/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.squareup.picasso;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import java.util.concurrent.Future;

class Dispatcher {
    static final int AIRPLANE_MODE_CHANGE = 0xa;
    private static final int AIRPLANE_MODE_OFF = 0x0;
    private static final int AIRPLANE_MODE_ON = 0x1;
    private static final int BATCH_DELAY = 0xc8;
    private static final String DISPATCHER_THREAD_NAME = "Dispatcher";
    static final int HUNTER_BATCH_COMPLETE = 0x8;
    static final int HUNTER_COMPLETE = 0x4;
    static final int HUNTER_DECODE_FAILED = 0x6;
    static final int HUNTER_DELAY_NEXT_BATCH = 0x7;
    static final int HUNTER_RETRY = 0x5;
    static final int NETWORK_STATE_CHANGE = 0x9;
    static final int REQUEST_BATCH_RESUME = 0xd;
    static final int REQUEST_CANCEL = 0x2;
    static final int REQUEST_GCED = 0x3;
    static final int REQUEST_SUBMIT = 0x1;
    private static final int RETRY_DELAY = 0x1f4;
    static final int TAG_PAUSE = 0xb;
    static final int TAG_RESUME = 0xc;
    boolean airplaneMode;
    final List<BitmapHunter> batch;
    final Cache cache;
    final Context context;
    final Dispatcher.DispatcherThread dispatcherThread;
    final Downloader downloader;
    final Map<Object, Action> failedActions;
    final Handler handler;
    final Map<String, BitmapHunter> hunterMap<String, BitmapHunter>;
    final Handler mainThreadHandler;
    final Map<Object, Action> pausedActions;
    final Set<Object> pausedTags;
    final Dispatcher.NetworkBroadcastReceiver receiver;
    final boolean scansNetworkChanges;
    final ExecutorService service;
    final Stats stats;
    
    Dispatcher(Context p1, ExecutorService p2, Handler p3, Downloader p4, Cache p5, Stats p6) {
        dispatcherThread = new Dispatcher.DispatcherThread();
        dispatcherThread.start();
        Utils.flushStackLocalLeaks(dispatcherThread.getLooper());
        context = p1;
        service = p2;
        hunterMap = new LinkedHashMap();
        failedActions = new WeakHashMap();
        pausedActions = new WeakHashMap();
        pausedTags = new HashSet();
        handler = new Dispatcher.DispatcherHandler(dispatcherThread.getLooper(), this);
        downloader = p4;
        mainThreadHandler = p3;
        cache = p5;
        stats = p6;
        batch = new ArrayList(0x4);
        airplaneMode = Utils.isAirplaneModeOn(context);
        scansNetworkChanges = Utils.hasPermission(p1, "android.permission.ACCESS_NETWORK_STATE");
        receiver = new Dispatcher.NetworkBroadcastReceiver(this);
        receiver.register();
    }
    
    void shutdown() {
        if(service instanceof PicassoExecutorService) {
            service.shutdown();
        }
        downloader.shutdown();
        dispatcherThread.quit();
        Picasso.HANDLER.post(new Runnable(this) {
            
            public void run() {
                receiver.unregister();
            }
        });
    }
    
    void dispatchSubmit(Action p1) {
        handler.sendMessage(handler.obtainMessage(0x1, p1));
    }
    
    void dispatchCancel(Action p1) {
        handler.sendMessage(handler.obtainMessage(0x2, p1));
    }
    
    void dispatchPauseTag(Object p1) {
        handler.sendMessage(handler.obtainMessage(0xb, p1));
    }
    
    void dispatchResumeTag(Object p1) {
        handler.sendMessage(handler.obtainMessage(0xc, p1));
    }
    
    void dispatchComplete(BitmapHunter p1) {
        handler.sendMessage(handler.obtainMessage(0x4, p1));
    }
    
    void dispatchRetry(BitmapHunter p1) {
        handler.sendMessageDelayed(handler.obtainMessage(0x5, p1), 0x1f4);
    }
    
    void dispatchFailed(BitmapHunter p1) {
        handler.sendMessage(handler.obtainMessage(0x6, p1));
    }
    
    void dispatchNetworkStateChange(NetworkInfo p1) {
        handler.sendMessage(handler.obtainMessage(0x9, p1));
    }
    
    void dispatchAirplaneModeChange(boolean p1) {
        handler.sendMessage(handler.obtainMessage(0xa, p1 ? 0x1 : 0x0, 0x0));
    }
    
    void performSubmit(Action p1) {
        performSubmit(p1, true);
    }
    
    void performSubmit(Action p1, boolean p2) {
        if(pausedTags.contains(p1.getTag())) {
            pausedActions.put(p1.getTarget(), p1);
            if(p1.getPicasso().loggingEnabled) {
                Utils.log("Dispatcher", "paused", p1.request.logId(), ().append("because tag \'"));
                return;
                localStringBuilder1 = ().append("because tag \'").append(p1.getTag()).append("\' is paused").toString();
            }
        }
        hunterMap = (BitmapHunter)hunterMap.get(p1.getKey());
        if(hunterMap != null) {
            hunterMap.attach(p1);
            return;
        }
        if(service.isShutdown()) {
            if(p1.getPicasso().loggingEnabled) {
                Utils.log("Dispatcher", "ignored", p1.request.logId(), "because shut down");
            }
            return;
        }
        p1.getPicasso().loggingEnabled = BitmapHunter.forRequest(p1.getPicasso(), this, cache, stats, p1);
        p1.getPicasso().future = service.submit(p1.getPicasso());
        hunterMap.put(p1.getKey(), p1.getPicasso());
        if(p2) {
            failedActions.remove(p1.getTarget());
        }
        if(p1.getPicasso().loggingEnabled) {
            Utils.log("Dispatcher", "enqueued", p1.request.logId());
        }
    }
    
    void performCancel(Action p1) {
        // :( Parsing error. Please contact me.
    }
    
    void performPauseTag(Object p1) {
        // :( Parsing error. Please contact me.
    }
    
    void performResumeTag(Object p1) {
        if(!pausedTags.remove(p1)) {
            return;
        }
        int localconst/41 = 0x0;
        while(pausedActions.values().iterator().hasNext()) {
            localboolean2 = (Action)pausedActions.values().iterator().next();
            if(pausedActions.values().iterator().hasNext().getTag().equals(p1)) {
                if(localconst/43 == null) {
                    ArrayList localconst/43 = new ArrayList();
                }
                localconst/43.add(pausedActions.values().iterator().hasNext());
                pausedActions.values().iterator().remove();
            }
        }
        if(localconst/41 != null) {
            mainThreadHandler.sendMessage(mainThreadHandler.obtainMessage(0xd, localconst/41));
        }
    }
    
    void performRetry(BitmapHunter p1) {
        if(p1.isCancelled()) {
            return;
        }
        if(service.isShutdown()) {
            performError(p1, false);
            return;
        }
        service.isShutdown() = false;
        if(scansNetworkChanges) {
            context = (ConnectivityManager)Utils.getService(context, "connectivity");
            "connectivity" = context.getActiveNetworkInfo();
            !"connectivity".isConnected() ? 0x1 : !"connectivity".isConnected() ? 0x1 = 0x0;
            localString1 = p1.shouldRetry(airplaneMode, "connectivity");
            airplaneMode = p1.supportsReplay();
            ("connectivity" == null)) {
                ((scansNetworkChanges) && (airplaneMode)) = 0x0;
                performError(p1, ((scansNetworkChanges) && (airplaneMode)));
                if(((scansNetworkChanges) && (airplaneMode))) {
                    markForReplay(p1);
                }
                return;
            }
            if((!scansNetworkChanges) || (!"connectivity".isConnected() ? 0x1 : !"connectivity".isConnected() ? 0x1 != null)) {
                if(p1.getPicasso().loggingEnabled) {
                    Utils.log("Dispatcher", "retrying", Utils.getLogIdsForHunter(p1));
                }
                if(p1.getException() instanceof NetworkRequestHandler.ContentLengthException) {
                    p1.networkPolicy = (p1.networkPolicy | NetworkPolicy.NO_CACHE.index);
                }
                p1.future = service.submit(p1);
                return;
            }
            performError(p1, airplaneMode);
            if(airplaneMode) {
                markForReplay(p1);
            }
        }
        scansNetworkChanges = service.isShutdown();
    }
    
    void performComplete(BitmapHunter p1) {
        if(MemoryPolicy.shouldWriteToMemoryCache(p1.getMemoryPolicy())) {
            cache.set(p1.getKey(), p1.getResult());
        }
        hunterMap.remove(p1.getKey());
        batch(p1);
        if(p1.getPicasso().loggingEnabled) {
            Utils.log("Dispatcher", "batched", Utils.getLogIdsForHunter(p1), "for completion");
        }
    }
    
    void performBatchComplete() {
        ArrayList localArrayList1 = new ArrayList(batch);
        batch.clear();
        mainThreadHandler.sendMessage(mainThreadHandler.obtainMessage(0x8, localArrayList1));
        logBatch(localArrayList1);
    }
    
    void performError(BitmapHunter p1, boolean p2) {
        if(p1.getPicasso().loggingEnabled) {
            Utils.log("Dispatcher", "batched", Utils.getLogIdsForHunter(p1), p2 ? " (will replay)" : "for error" + "");
        }
        hunterMap.remove(p1.getKey());
        batch(p1);
    }
    
    void performAirplaneModeChange(boolean p1) {
        airplaneMode = p1;
    }
    
    void performNetworkStateChange(NetworkInfo p1) {
        if(service instanceof PicassoExecutorService) {
            (PicassoExecutorService)service.adjustThreadCount(p1);
        }
        if((p1 != null) && (p1.isConnected())) {
            flushFailedActions();
        }
    }
    
    private void flushFailedActions() {
        // :( Parsing error. Please contact me.
    }
    
    private void markForReplay(BitmapHunter p1) {
        // :( Parsing error. Please contact me.
    }
    
    private void markForReplay(Action p1) {
        // :( Parsing error. Please contact me.
    }
    
    private void batch(BitmapHunter p1) {
        if(p1.isCancelled()) {
            return;
        }
        batch.add(p1);
        if(!handler.hasMessages(0x7)) {
            handler.sendEmptyMessageDelayed(0x7, 0xc8);
        }
    }
    
    private void logBatch(List<BitmapHunter> p1) {
        if((p1 == null) || (p1.isEmpty())) {
            return;
        }
        0x0.loggingEnabled = (BitmapHunter)p1.get(0x0);
        0x0.loggingEnabled = 0x0.getPicasso();
        if(0x0.loggingEnabled) {
            for(; p1.iterator().hasNext(); localString1) {
                if(().length() > 0) {
                    ().append(", ");
                }
                ().append(Utils.getLogIdsForHunter(p1.iterator().hasNext()));
            }
            Utils.log("Dispatcher", "delivered", ().toString());
        }
    }
    
    class DispatcherHandler extends Handler {
        private final Dispatcher dispatcher;
        
        public DispatcherHandler(Looper p1, Dispatcher p2) {
            super(p1);
            dispatcher = p2;
        }
        
        public void handleMessage(Message p1) {
            switch(p1.what) {
                case 1:
                {
                    int localconst/41 = p1.obj;
                    dispatcher.performSubmit((Action)0x1);
                    return;
                }
                case 2:
                {
                    int localconst/42 = p1.obj;
                    dispatcher.performCancel((Action)0x1);
                    return;
                }
                case 11:
                {
                    int localconst/43 = p1.obj;
                    dispatcher.performPauseTag(0x1);
                    return;
                }
                case 12:
                {
                    int localconst/44 = p1.obj;
                    dispatcher.performResumeTag(0x1);
                    return;
                }
                case 4:
                {
                    int localconst/45 = p1.obj;
                    dispatcher.performComplete((BitmapHunter)0x1);
                    return;
                }
                case 5:
                {
                    int localconst/46 = p1.obj;
                    dispatcher.performRetry((BitmapHunter)0x1);
                    return;
                }
                case 6:
                {
                    int localconst/47 = p1.obj;
                    dispatcher.performError((BitmapHunter)0x1, false);
                    return;
                }
                case 7:
                {
                    dispatcher.performBatchComplete();
                    return;
                }
                case 9:
                {
                    int localconst/48 = p1.obj;
                    dispatcher.performNetworkStateChange((NetworkInfo)0x1);
                    return;
                }
                case 10:
                {
                    if(p1.arg1 == 0x1) {
                        dispatcher.performAirplaneModeChange(true);
                    }
                    0x1 = 0x0;
                    return;
                }
                case 3:
                case 8:
                {
                    Picasso.HANDLER.post(new Runnable(this, p1) {
                        
                        public void run() {
                            throw new AssertionError("Unknown handler message received: " + msg.what);
                        }
                    });
                    break;
                }
            }
        }
    }
    
    class DispatcherThread extends HandlerThread {
        
        DispatcherThread() {
            super("Picasso-Dispatcher", 0xa);
        }
    }
    
    class NetworkBroadcastReceiver extends BroadcastReceiver {
        static final String EXTRA_AIRPLANE_STATE = "state";
        private final Dispatcher dispatcher;
        
        NetworkBroadcastReceiver(Dispatcher p1) {
            dispatcher = p1;
        }
        
        void register() {
            IntentFilter localIntentFilter1 = new IntentFilter();
            localIntentFilter1.addAction("android.intent.action.AIRPLANE_MODE");
            if(dispatcher.scansNetworkChanges) {
                localIntentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            dispatcher.context.registerReceiver(this, localIntentFilter1);
        }
        
        void unregister() {
            dispatcher.context.unregisterReceiver(this);
        }
        
        public void onReceive(Context p1, Intent p2) {
            // :( Parsing error. Please contact me.
        }
    }
}
