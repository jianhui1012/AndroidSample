package com.golike.myapplication.ui.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.golike.myapplication.customviews.beisaier.BeSaiErView;
import com.golike.myapplication.customviews.beisaier.PaintBeSaiErView;
import com.golike.myapplication.customviews.hencoder.BasicShape;
import com.golike.myapplication.customviews.hencoder.PathView;
import com.golike.myapplication.customviews.hencoder.PropetyAnimationView;

/**
 * @author admin
 */
public class ViewContentActivity extends AppCompatActivity {

    PropetyAnimationView propetyAnimationView1,propetyAnimationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        switch (getIntent().getIntExtra("index", -1)) {
            case 0:
                ll.addView(new BeSaiErView(this));
                break;
            case 1:
                ll.addView(new PaintBeSaiErView(this));
                break;
            case 2:
                ll.addView(new BasicShape(this));
                break;
            case 3:
                ll.addView(new PathView(this));
                break;
            case 4:
                propetyAnimationView1 = new PropetyAnimationView(this);
                ll.addView(propetyAnimationView1);
//                propetyAnimationView2 = new PropetyAnimationView(this);
//                ll.addView(propetyAnimationView2);
                break;
            default:
                break;
        }
        setContentView(ll);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(propetyAnimationView1, "progress", 360);
        objectAnimator1.setDuration(2000);
        objectAnimator1.start();

//        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(propetyAnimationView2, "progress", 360);
//        objectAnimator2.setDuration(2000);
//        objectAnimator2.start();
    }
}
