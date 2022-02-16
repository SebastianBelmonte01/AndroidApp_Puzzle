package com.example.belmontesebastian_puzzle;

import android.widget.TextView;

public class Piece {
    private int index;
    private TextView textView;

    public Piece(int index, TextView textView) {
        this.index = index;
        this.textView = textView;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
