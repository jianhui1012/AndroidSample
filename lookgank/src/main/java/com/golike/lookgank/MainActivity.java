package com.golike.lookgank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscriber;
import rx.internal.util.ObserverSubscriber;

public class MainActivity extends AppCompatActivity {

    private Subscriber<SearchData> subscriber;

    @BindView(R.id.getData)
    public Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriber = new Subscriber<SearchData>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this,"请求完成",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchData searchData) {
                        Toast.makeText(MainActivity.this, searchData.results.size()+"",Toast.LENGTH_LONG).show();
                    }
                };
                HttpUtils.getInstance().getHomeData(subscriber,"Android",10,1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
