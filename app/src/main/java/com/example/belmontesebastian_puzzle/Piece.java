package com.example.belmontesebastian_puzzle;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import java.util.Vector;

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

    public void exchangedPieces(int origin, int destination, Vector<Piece> puzzle){
        Drawable drawable = puzzle.get(destination).getTextView().getBackground();
        puzzle.get(destination).getTextView().setBackground(puzzle.get(origin).getTextView().getBackground());
        puzzle.get(destination).getTextView().setText(puzzle.get(origin).getTextView().getText());
        puzzle.get(origin).getTextView().setText("");
        puzzle.get(origin).getTextView().setBackground(drawable);

        int auxIndex = puzzle.get(origin).getIndex();

        puzzle.get(origin).setIndex(puzzle.get(destination).getIndex());
        puzzle.get(destination).setIndex(auxIndex);

    }
}
