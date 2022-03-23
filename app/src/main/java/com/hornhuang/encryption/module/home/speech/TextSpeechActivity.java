package com.hornhuang.encryption.module.home.speech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hornhuang.encryption.R;
import com.hornhuang.encryption.module.base.activity.BaseActivity;
import com.hornhuang.encryption.utils.DateUtil;

import java.io.File;
import java.util.Locale;

public class TextSpeechActivity extends BaseActivity
        implements View.OnClickListener,
        TextToSpeech.OnInitListener,
        SeekBar.OnSeekBarChangeListener {

    private EditText mSpeechEditView;
    private EditText mPathEditView;
    private SeekBar mToneBar;
    private SeekBar mSpeedBar;
    private TextView mToneText;
    private TextView mSpeedText;
    private Button mSpeekBtn;
    private Button mSaveBtn;

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_speech);

        config();

    }

    private void config() {
        mSpeechEditView = findViewById(R.id.input_speech);
        mPathEditView = findViewById(R.id.input_save_path);
        mToneBar = findViewById(R.id.tone);
        mSpeedBar = findViewById(R.id.speed);
        mToneText = findViewById(R.id.tone_text);
        mSpeedText = findViewById(R.id.speed_text);
        mSpeekBtn = findViewById(R.id.speak);
        mSaveBtn = findViewById(R.id.save);

        tts = new TextToSpeech(this, this);
        //tts.setLanguage(Locale.US);
        tts.setLanguage(Locale.CHINA);

        mSpeekBtn.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);

        mToneBar.setOnSeekBarChangeListener(this);
        mSpeedBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.speak:
                tts.speak(mSpeechEditView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.save:
                tts.synthesizeToFile(mPathEditView.getText().toString(), null , new File(getSDCardBaseDir(), "云密" + DateUtil.getCurTimeFully() + ".wav"), DateUtil.getCurTimeFully() + ".wav");
                break;
            default:
                Toast.makeText(this, "无效点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getSDCardBaseDir() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return  null;
    }

    private boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onInit(int status) {
        // 判断是否转化成功
        if (status == TextToSpeech.SUCCESS) {
            //默认设定语言为中文，原生的android貌似不支持中文。
            int result = tts.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language is not available.", Toast.LENGTH_SHORT).show();
                tts.setLanguage(Locale.US);
            }
        }
    }

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, TextSpeechActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float floatProgress = (float)progress / 100;
        if (seekBar == mToneBar) {
            mToneText.setText("音调：" + floatProgress);
            tts.setPitch((float)floatProgress);
        } else if (seekBar == mSpeedBar) {
            mSpeedText.setText("语速：" + floatProgress);
            tts.setSpeechRate((float)floatProgress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i("TextSpeechActivity", "开始滑动！");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i("TextSpeechActivity", "停止滑动！");
    }
}