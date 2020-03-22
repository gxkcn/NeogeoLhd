/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.fbagamehjdt;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.content.Context;
import com.fbagamehjdt.api.ResUtil;
import com.arcade.activity.simulator.BaseActivity;
import android.support.v4.view.PagerAdapter;
import android.widget.LinearLayout;
import java.util.ArrayList;
import android.view.View;
import java.util.Timer;
import android.widget.TextView;
import java.util.Date;
import android.widget.Toast;
import java.util.HashMap;
import com.umeng.analytics.MobclickAgent;
import java.util.Map;
import android.widget.RelativeLayout;
import android.view.ViewGroup;
import com.fbagamehjdt.api.Util;
import java.util.TimerTask;
import android.view.KeyEvent;
import android.content.Intent;
import com.arcade.activity.simulator.BaseGameActivity;
import android.os.Bundle;
import android.view.Window;
import com.umeng.commonsdk.UMConfigure;

public class IdentificationActivity extends BaseActivity {
    PagerAdapter a;
    private String b;
    private Context c;
    private ImageView d;
    private ViewPager e;
    private ImageView f;
    private ImageView g;
    private LinearLayout h;
    private ImageView i;
    private ImageView[] j;
    private ArrayList<View> k;
    private Timer l;
    private TextView m;
    private int n;
    private String o;
    private TextView p;
    private boolean q;
    
    public IdentificationActivity() {
        b = "IdentificationActivity";
        n = -0x1;
        o = "1545235200000";
        q = true;
        a = new PagerAdapter(this) {
            
            2(IdentificationActivity p1) {
            }
            
            public int getCount() {
                return 0x2;
            }
            
            public boolean isViewFromObject(View p1, Object p2) {
                return (p1 == p2);
            }
            
            public void destroyItem(View p1, int p2, Object p3) {
                p1.removeView((View)c(a).get(p2));
            }
            
            public Object instantiateItem(View p1, int p2) {
                p1.addView((View)c(a).get(p2));
                return c(a).get(p2);
            }
        };
    }
    
    protected void onCreate(Bundle p1) {
        setRequestedOrientation(0x0);
        MobclickAgent.EScenarioType.E_UM_NORMAL = getWindow();
        requestWindowFeature(0x1);
        0x1 = 0x400;
        0x0.setFlags(0x1, 0x1);
        super.onCreate(p1);
        c = this;
        Util.a(this);
        c();
        UMConfigure.init(this, "55e568c3e0f55a31b8004bc4", "xugame_ifashiongame", 0x2, "");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        a(this);
    }
    
    private void a() {
        Date locallong1 = new Date();
        locallong1 = Long.valueOf(locallong1.getTime());
        if(locallong1.getTime().longValue() >= Long.parseLong(o)) {
            Toast.makeText(c, "\u8be5\u6e38\u620f\u4e3a\u6d4b\u8bd5\u7248\uff0c\u6d4b\u8bd5\u65f6\u95f4\u5df2\u8fc7\u671f\u3002\u3002\u3002", 0x1).show();
            finish();
        }
    }
    
    public static void a(Context p1) {
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("packageName", getPackageName());
        MobclickAgent.onEvent(p1, "StartGame", localHashMap1);
    }
    
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    
    private void b() {
        RelativeLayout localRelativeLayout1 = new RelativeLayout(c);
        d = new ImageView(c);
        RelativeLayout.LayoutParams localRelativeLayout.LayoutParams2 = new RelativeLayout.LayoutParams(-0x1, -0x1);
        localRelativeLayout1.addView(d, localRelativeLayout.LayoutParams2);
        m = new TextView(c);
        m.setText("\u70b9\u51fb\u4efb\u610f\u6309\u952e\u8fdb\u5165\u6e38\u620f");
        m.setTextSize(30.0f);
        m.setTextColor(0xff000000);
        RelativeLayout.LayoutParams localRelativeLayout.LayoutParams2 = new RelativeLayout.LayoutParams(-0x2, -0x2);
        localRelativeLayout.LayoutParams2.addRule(0xc);
        localRelativeLayout.LayoutParams2.addRule(0xe);
        localRelativeLayout.LayoutParams2.bottomMargin = 0x32;
        localRelativeLayout1.addView(m, localRelativeLayout.LayoutParams2);
        setContentView(localRelativeLayout1);
    }
    
    private void c() {
        RelativeLayout locallong1 = new RelativeLayout(c);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x1, -0x1);
        locallong1.setLayoutParams(l);
        e = new ViewPager(this);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x1, -0x1);
        locallong1.addView(e, l);
        h = new LinearLayout(this);
        h.setOrientation(0x0);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x2, -0x2);
        l.bottomMargin = Util.a(0x1e);
        l.addRule(0xc);
        l.addRule(0xe);
        locallong1.addView(h, l);
        m = new TextView(c);
        m.setText("\u70b9\u51fb\u786e\u5b9a(OK)\u952e\u8fdb\u5165\u6e38\u620f");
        m.setTextSize(0x0, (float)Util.a(0x1e));
        m.setTextColor(0xff000000);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x2, -0x2);
        l.addRule(0xc);
        l.addRule(0xe);
        l.bottomMargin = Util.a(0x3c);
        locallong1.addView(m, l);
        p = new TextView(c);
        p.setText("\u89c6\u535a\u4e91\u79d1\u6280");
        p.setTextSize(0x0, (float)Util.a(0x16));
        p.setTextColor(0xff000000);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x2, -0x2);
        l.addRule(0xc);
        l.addRule(0xb);
        l.bottomMargin = Util.a(0x1e);
        l.rightMargin = Util.a(0x1e);
        locallong1.addView(p, l);
        f = new ImageView(this);
        f.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x1, -0x1);
        l.addRule(0xc);
        l.addRule(0xe);
        f.setBackgroundResource(ResUtil.b(this, "kof_sb"));
        g = new ImageView(this);
        g.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-0x1, -0x1);
        l.addRule(0xc);
        l.addRule(0xe);
        g.setBackgroundResource(ResUtil.b(this, "kof_yk"));
        k = new ArrayList();
        k.add(g);
        k.add(f);
        new ImageView[0x2] = 0x0;
        for(; new ImageView[0x2] < 0x2; new ImageView[0x2] = new ImageView[0x2] + 0x1) {
            i = new ImageView(this);
            i.setLayoutParams(new RelativeLayout.LayoutParams(Util.a(0x14), Util.a(0x14)));
            i.setPadding(Util.a(0x14), 0x0, Util.a(0x14), 0x0);
            j[new ImageView[0x2]] = i;
            if(new ImageView[0x2] == null) {
                j[new ImageView[0x2]].setBackgroundResource(ResUtil.b(c, "page_indicator_focused"));
            } else {
                j[new ImageView[0x2]].setBackgroundResource(ResUtil.b(c, "page_indicator_unfocused"));
            }
            h.addView(j[new ImageView[0x2]]);
        }
        e.setAdapter(a);
        e.setOnPageChangeListener(new IdentificationActivity.GuidePageChangeListener(this));
        setContentView(locallong1);
        l = new Timer();
        l.schedule(d(), 0x1, 0x3e8);
    }
    
    private TimerTask d() {
        IdentificationActivity.1 localIdentificationActivity.11 = new TimerTask(this) {
            
            1(IdentificationActivity p1) {
            }
            
            public void run() {
                a.runOnUiThread(new Runnable(this) {
                    
                    1(IdentificationActivity.1 p1) {
                    }
                    
                    public void run() {
                        if(a(a.a)) {
                            a(a.a, false);
                            b(a.a).setTextColor(0x0);
                            return;
                        }
                        a(a.a, true);
                        b(a.a).setTextColor(0xff000000);
                    }
                });
            }
        };
        return localIdentificationActivity.11;
    }
    
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        
        GuidePageChangeListener(IdentificationActivity p1) {
        }
        
        public void onPageScrollStateChanged(int p1) {
        }
        
        public void onPageScrolled(int p1, float p2, int p3) {
        }
        
        public void onPageSelected(int p1) {
            for(const/4  = 0x0; 0x0 < d(a).length; 0x0 = 0x0 + 0x1) {
                d(a)[p1].setBackgroundResource(ResUtil.b(e(a), "page_indicator_focused"));
                if(p1 != 0x0) {
                    d(a)[0x0].setBackgroundResource(ResUtil.b(e(a), "page_indicator_unfocused"));
                }
            }
        }
    }
    
    public boolean dispatchKeyEvent(KeyEvent p1) {
        if(p1.getAction() == 0) {
            if((p1.getKeyCode() != 0x16) && (p1.getKeyCode() != 0x15)) {
                Intent l = new Intent(this, BaseGameActivity.class);
                startActivity(l);
                if(l != null) {
                    l.cancel();
                }
                finish();
            }
        }
        return super.dispatchKeyEvent(p1);
    }
}
