package com.golike.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.golike.myapplication.customviews.beisaier.BeSaiErView;
import com.golike.myapplication.customviews.beisaier.PaintBeSaiErView;
import com.golike.myapplication.customviews.hencoder.BasicShape;
import com.golike.myapplication.customviews.hencoder.PathView;

/**
 * @author admin
 */
public class ViewContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
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
            default:
                break;
        }
        setContentView(ll);
    }
}
