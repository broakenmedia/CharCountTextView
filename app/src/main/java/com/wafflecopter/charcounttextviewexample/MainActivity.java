package com.wafflecopter.charcounttextviewexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.wafflecopter.charcounttextview.CharCountTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharCountTextView tvCharCount = (CharCountTextView) findViewById(R.id.tvTextCounter);
        EditText etTweetContent = (EditText) findViewById(R.id.etCounterText);
        final Button btnTweet = (Button) findViewById(R.id.btnTweet);

        tvCharCount.setEditText(etTweetContent);
        tvCharCount.setMaxCharacters(150); //Will default to 150 anyway (Twitter emulation)
        tvCharCount.setExceededTextColor(Color.RED); //Will default to red also
        tvCharCount.setCharCountChangedListener(new CharCountTextView.CharCountChangedListener() {
            @Override
            public void onCountChanged(int countRemaining, boolean hasExceededLimit) {
                if (hasExceededLimit) {
                    btnTweet.setEnabled(false);
                } else {
                    btnTweet.setEnabled(true);
                }
            }
        });

    }
}
