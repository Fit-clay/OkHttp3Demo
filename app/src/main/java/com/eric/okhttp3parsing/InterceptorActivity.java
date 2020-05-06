package com.eric.okhttp3parsing;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class InterceptorActivity  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
        findViewById(R.id.tv_inter_log).setOnClickListener(v->{

        });
    }
}
