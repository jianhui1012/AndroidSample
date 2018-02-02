package com.golike.myapplication.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @author admin
 */
public class ViewListActivity extends AppCompatActivity {

    static final String[] showTitles = new String[]{"二阶贝塞尔曲线", "光滑曲线","绘制基本图形","Path图形"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < showTitles.length; i++) {
            Button button = new Button(this);
            button.setText(showTitles[i]);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewListActivity.this, ViewContentActivity.class);
                    intent.putExtra("index", finalI);
                    startActivity(intent);
                }
            });
            ll.addView(button);
        }
        setContentView(ll);
    }
}
