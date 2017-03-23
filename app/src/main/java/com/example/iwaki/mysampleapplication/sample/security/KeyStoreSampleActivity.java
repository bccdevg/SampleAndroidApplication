package com.example.iwaki.mysampleapplication.sample.security;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iwaki.mysampleapplication.R;

import java.io.UnsupportedEncodingException;

public class KeyStoreSampleActivity extends AppCompatActivity {

    private static String ANDROID_KEY_STORE_ALIAS = "android_key_store_alias";
    private EditText cipherTarget;
    private TextView encryptedValue;
    private TextView decryptedValue;
    private AndroidKeyStoreManager androidKeyStoreManager;
    private String savedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_store_sample);

        cipherTarget = (EditText) findViewById(R.id.cipherTarget);
        encryptedValue = (TextView) findViewById(R.id.encryptedValue);
        decryptedValue = (TextView) findViewById(R.id.decryptedValue);

        androidKeyStoreManager = AndroidKeyStoreManager.getInstance(this);
    }

    public void onEncryptButtonTapped(View view) {
        String input = cipherTarget.getText().toString();
        if (!TextUtils.isEmpty(input)) {
            byte[] encryptedByteValue = new byte[0];
                encryptedByteValue = androidKeyStoreManager.encrypt(input.getBytes());
            try {
                savedData = new String(encryptedByteValue, "iso-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            encryptedValue.setText(savedData);
        }
    }

    public void onDecryptedButtonTapped(View view) {
        if (savedData != null) {
            byte[] decryptedByteValue = new byte[0];
            try {
                decryptedByteValue = androidKeyStoreManager.decrypt(savedData.getBytes("iso-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            decryptedValue.setText(new String(decryptedByteValue));
        }
    }
}
