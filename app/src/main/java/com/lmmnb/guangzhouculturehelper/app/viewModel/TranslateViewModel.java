package com.lmmnb.guangzhouculturehelper.app.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.lmmnb.guangzhouculturehelper.api.apis.Language;
import com.lmmnb.guangzhouculturehelper.api.model.TranslateModel;
import com.lmmnb.guangzhouculturehelper.app.utils.MediaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TranslateViewModel extends BaseObservable {

    private Context context;
    private TranslateModel translate;

    private List<String> demo;

    public TranslateViewModel(Context context) {
        this.context = context;

        // Demo data
        demo = new ArrayList<>();
        demo.add("你是谁？");
        demo.add("你要去哪儿？");
        demo.add("我觉得你说的对。");
        demo.add("对不起，是我错了。");
        demo.add("连学长太厉害了。");

    }

    public void getDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                translate = Language.translateToCantoneseByText(demo.get(new Random().nextInt(demo.size())));

                // 当数据修改后，一定要显式调用 notifyChange()。
                // 当然你也可以实现一个 Interface ，在 interface 中用 Callback 的方式做 notifyChange()
                notifyChange();
            }
        }).start();
    }

    public TranslateModel getTranslate() {
        return translate;
    }

    public void setTranslate(TranslateModel translate) {
        this.translate = translate;
    }

    public void translateToCantoneseByText(String srcText) {
        translate = Language.translateToCantoneseByText(srcText);
    }

    public void tts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaUtil.playMp3(Language.speech(translate.getTgtText()));
            }
        }).start();
    }

//    public void translateToMadarinByVoice()

}
