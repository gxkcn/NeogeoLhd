/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.arcade.activity.simulator;

import android.opengl.GLSurfaceView;
import android.media.AudioTrack;
import android.util.Log;

public class EmuMedia {
    private static BaseGameActivity mEmuActivity;
    private static GLSurfaceView mGLESView;
    private static AudioTrack track;
    private static float volume = AudioTrack.getMaxVolume();
    
    static void destroy() {
        if(track != null) {
            track.stop();
            track = 0x0;
        }
    }
    
    public static void setGLView(GLSurfaceView p1) {
        mGLESView = p1;
    }
    
    static void GLESFlip() {
        mGLESView.requestRender();
    }
    
    public static void setEmuActivity(BaseGameActivity p1) {
        mEmuActivity = p1;
    }
    
    static void ActivityFinish() {
        if(mEmuActivity != null) {
            mEmuActivity.finish();
        }
    }
    
    static void updateOption(int p1, int p2, String p3, String p4, int p5, int p6) {
        // :( Parsing error. Please contact me.
    }
    
    static boolean audioCreate(int p1, int p2, int p3) {
        Log.w("*************", "rate = " + p1 + "\n");
        Log.w("*************", "bits = " + p2 + "\n");
        Log.w("*************", "channels = " + p3 + "\n");
        p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2 = 0x3;
        if(p3 == 0x2) {
        }
        0x3 = 0x2;
        if(track != null) {
            if(track.getSampleRate() == p1) {
                if(track.getAudioFormat() == p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2) {
                    if(track.getChannelCount() == p3) {
                        return true;
                    }
                }
            }
        }
        AudioTrack.getMinBufferSize(p1, 0x3, p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2) = AudioTrack.getMinBufferSize(p1, 0x3, p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2) * 0x2;
        if(AudioTrack.getMinBufferSize(p1, 0x3, p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2) < 0x5dc) {
            try {
                track = new AudioTrack(0x3, p2, 0x3, p3, 0x5dc, 0x1);
                if(track.getState() == 0) {
                    track = 0x0;
                }
            } catch(IllegalArgumentException localIllegalArgumentException1) {
                track = 0x0;
            }
            if(track == null) {
                return false;
            }
            track.setStereoVolume(volume, volume);
            return true;
        }
        0x5dc = AudioTrack.getMinBufferSize(p1, 0x3, p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2);
        return AudioTrack.getMinBufferSize(p1, 0x3, p2 == 0x10 ? 0x2 : p2 == 0x10 ? 0x2);
    }
    
    static void audioSetVolume(int p1) {
        // :( Parsing error. Please contact me.
    }
    
    static void audioDestroy() {
        if(track != null) {
            track.stop();
            track = 0x0;
        }
    }
    
    static void audioStart() {
        if(track != null) {
            track.play();
        }
    }
    
    static void audioStop() {
        if(track != null) {
            track.stop();
            track.flush();
        }
    }
    
    static void audioPause() {
        if(track != null) {
            track.pause();
        }
    }
    
    static void audioPlay(byte[] p1, int p2) {
        if(track != null) {
            track.write(p1, 0x0, p2);
        }
    }
}