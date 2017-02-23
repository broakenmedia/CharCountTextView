package com.wafflecopter.charcounttextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class CharCountTextView extends TextView {

    private int maxLength = 150; //Emulate Twitter by Default
    private int exceededTextColor = Color.RED;
    private int defaultTextColor = Color.BLACK; //Only Used in Context-Only Constructor
    private CharCountChangedListener listener;

    public interface CharCountChangedListener {
        void onCountChanged(int countRemaining, boolean hasExceededLimit);
    }

    public CharCountTextView(Context context) {
        super(context);
        init();
    }

    public CharCountTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAttrs(context, attrs);
        init();
    }

    public CharCountTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttrs(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CharCountTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        handleAttrs(context, attrs);
        init();
    }

    public void setCharCountChangedListener(CharCountChangedListener listener) {
        this.listener = listener;
    }

    public void setEditText(EditText targetEt) {
        targetEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Nothing here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Nothing here
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String enteredText = editable.toString();
                int countRemaining = maxLength - enteredText.length();
                boolean hasExceededLimit = enteredText.length() > maxLength;
                if (countRemaining <= (Math.round(maxLength * 0.1))) {
                    setTextColor(exceededTextColor);
                } else {
                    setTextColor(defaultTextColor);
                }
                setText(String.valueOf(countRemaining));
                if (listener != null) {
                    listener.onCountChanged(countRemaining, hasExceededLimit);
                } else {
                    throw new NullPointerException("A TextCountDownListener has not been set!");
                }
            }
        });
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxLength = maxCharacters;
        init();
    }

    public void setExceededTextColor(int textColor) {
        this.exceededTextColor = textColor;
    }

    private void handleAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CharCountTextView);
        this.exceededTextColor = a.getColor(R.styleable.CharCountTextView_exceededTextColor, Color.RED);
        this.maxLength = a.getInt(R.styleable.CharCountTextView_maxCharacters, 150);
        this.defaultTextColor = getCurrentTextColor();
        a.recycle();
    }

    private void init() {
        setText(String.valueOf(maxLength));
        //Something else if needed
    }

}
