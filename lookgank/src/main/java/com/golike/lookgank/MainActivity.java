package com.golike.lookgank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import rx.Observer;
import rx.Subscriber;
import rx.internal.util.ObserverSubscriber;

public class MainActivity extends AppCompatActivity {

    private Subscriber<SearchData> subscriber;

    @BindView(R.id.getData)
    private Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriber = new Subscriber<SearchData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchData searchData) {

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
