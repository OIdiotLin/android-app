package com.lmmnb.guangzhouculturehelper.app.utils;

import android.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MediaUtil {
    public static void playMp3(byte[] mp3arr) {
        final MediaPlayer player = new MediaPlayer();
        player.reset();
        try {
            File tempMp3 = File.createTempFile("tts", "mp3");
            tempMp3.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(mp3arr);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            player.setDataSource(fis.getFD());

            player.prepare();
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
