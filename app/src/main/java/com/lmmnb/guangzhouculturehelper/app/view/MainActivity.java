package com.lmmnb.guangzhouculturehelper.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lmmnb.guangzhouculturehelper.R;
import com.lmmnb.guangzhouculturehelper.api.apis.Language;
import com.lmmnb.guangzhouculturehelper.api.apis.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = Test.getIp();
                Log.i("demo", String.format("getIp: %s", ip));

                String mandarin = "你在哪里呀？";
                String cantonese = Language.translateToCantoneseByText(mandarin).getTgtText();
                Log.i("demo", String.format("translate: %s -> %s", mandarin, cantonese));
            }
        }).start();

    }


}
