package com.lmmnb.guangzhouculturehelper.api.apis;

import com.google.gson.Gson;
import com.lmmnb.guangzhouculturehelper.api.model.TranslateModel;
import com.lmmnb.guangzhouculturehelper.api.services.HttpUtil;
import com.lmmnb.guangzhouculturehelper.api.services.Url;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Package: com.lmmnb.guangzhouculturehelper.api.apis
 * Created by oidiotlin on 18-3-24.
 */

public class Language {

    /**
     * API: Translate Mandarin to Cantonese by text.
     * <p>
     * Request Example:
     * <pre>
     * {
     *     "from": "text",
     *     "to": "cantonese",
     *     "text": "请求一段语音或文本的翻译。"
     * }
     * </pre>
     * <p>
     * Response Example:
     * <pre>
     * {
     *     "from": "source-text",
     *     "to": "target-text"
     * }
     * </pre>
     *
     * @param srcText source text string
     * @return translateModel object
     */
    public static TranslateModel translateToCantoneseByText(String srcText) {
        JSONObject params;
        try {
            params = new JSONObject()
                    .put("from", "text")
                    .put("to", "cantonese")
                    .put("text", srcText);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("info", params.toString())
                .build();
        String json = HttpUtil.post(Url.translate, body);
        return new Gson().fromJson(json, TranslateModel.class);
    }

    /**
     * API: Get TTS as Cantonese.
     * use cantonese text as the parameter
     * <p>
     * Request Example:
     * <pre>
     * {
     *     "text": "请求一段语音"
     * }
     * </pre>
     *
     * @param text source Cantonese text
     * @return mp3 binary file as byte array
     */
    public static byte[] speech(String text) {
        JSONObject params;
        try {
            params = new JSONObject().put("text", text);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), params.toString());

        return HttpUtil.postByteArray(Url.speech, body);

    }
}
