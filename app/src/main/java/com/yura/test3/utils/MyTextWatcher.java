package com.yura.test3.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class MyTextWatcher implements TextWatcher {

    public interface TextWatcherDelegate {
        void validate(int id);
    }

    private TextWatcherDelegate delegate;

    private View view;

    public MyTextWatcher(View view, TextWatcherDelegate delegate) {
        this.view = view;
        this.delegate = delegate;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
        delegate.validate(view.getId());
    }
}
