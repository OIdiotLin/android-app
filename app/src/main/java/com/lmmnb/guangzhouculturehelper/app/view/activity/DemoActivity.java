package com.lmmnb.guangzhouculturehelper.app.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lmmnb.guangzhouculturehelper.R;
import com.lmmnb.guangzhouculturehelper.app.viewModel.TranslateViewModel;
import com.lmmnb.guangzhouculturehelper.databinding.ActivityDemoBinding;

public class DemoActivity extends AppCompatActivity {

    private TranslateViewModel translateVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 注意此处用 DataBindingUtil.setContentView 替换缺省的布局函数，
        // 这里返回了 dataBinding 类，用于做数据绑定。
        ActivityDemoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);

        // View - DemoActivity 拥有一个
        translateVM = new TranslateViewModel(this);

        binding.setVm(translateVM);

    }


    public void onRefreshClick(View view) {
        translateVM.getDemo();
    }

    public void onTTSClick(View view) {
        translateVM.tts();
    }
}
