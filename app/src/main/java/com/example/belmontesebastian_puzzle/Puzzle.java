package com.example.belmontesebastian_puzzle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Puzzle {
    public Vector<Piece> puzzle;
    int c = 0;
    TextView tvMovement;
    public Drawable blackBackground;




    private Vector <Drawable> backgrounds;

    public Puzzle(Vector<Piece> puzzle) {
        this.puzzle = puzzle;
        blackBackground = puzzle.get(8).getTextView().getBackground();
        backgrounds = savingBackgrounds();

    }

    public Vector<Piece> getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Vector<Piece> puzzle) {
        this.puzzle = puzzle;
    }

    public void clickReceived(TextView tvMov, MainActivity m) {
        for(int i = 0; i < this.puzzle.size(); i++) {
            final int ii = i;
            puzzle.get(i).getTextView().setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                   c++;
                   tvMov.setText(String.valueOf(c));
                   Piece clickedPiece = puzzle.get(ii);

                   System.out.println("Click in piece with index: " + ii );
                   System.out.println(ii);

                   if((ii < 2  || ii < 5 || ii < 8) && puzzle.get(ii + 1).getTextView().getText().toString().length() == 0){
                       System.out.println("El de mi derecha es vacio");
                       puzzle.get(ii).exchangedPieces(ii, ii+1,puzzle);
                   }
                   else{
                       if((ii > 0 || ii > 3 || ii > 6) && puzzle.get(ii - 1).getTextView().getText().toString().length() == 0){
                           puzzle.get(ii).exchangedPieces(ii, ii-1,puzzle);
                       }
                       else{
                           if(ii - 3 >= 0 && puzzle.get(ii - 3).getTextView().getText().length() == 0){
                               puzzle.get(ii).exchangedPieces(ii, ii-3,puzzle);
                           }
                           else if(ii + 3 > 0 && ii != 8 && puzzle.get(ii + 3).getTextView().getText().length() == 0){
                               puzzle.get(ii).exchangedPieces(ii, ii+3,puzzle);
                           }
                       }
                   }
                    if(!isFinished()){
                        System.out.println("No terminado");
                    }
                    else{
                        System.out.println("Terminado");
                        finishedMessage(m);
                    }
               }

            });

        }
    }

    public void mixing(TextView tvMov){
        tvMovement = tvMov;
        c=0;
        tvMovement.setText(String.valueOf(c));
        ArrayList<Integer> mixedIndexes = generateIntSet();
        Drawable black = this.puzzle.get(8).getTextView().getBackground();

        for(int i = 0; i < this.puzzle.size(); i++){
            this.puzzle.get(i).setIndex(mixedIndexes.get(i));
            //System.out.println("Nuevo index en: " + this.puzzle.get(i).getTextView().getText() );


            System.out.println(this.puzzle.get(mixedIndexes.get(i)).getTextView().getText());
           if(this.puzzle.get(i).getIndex() != 8){
                this.puzzle.get(i).getTextView().setText(String.valueOf(mixedIndexes.get(i)));

           }
           else{
               this.puzzle.get(i).getTextView().setText("");
               this.puzzle.get(i).getTextView().setBackground(blackBackground);
           }
           this.puzzle.get(i).getTextView().setBackground(backgrounds.get(this.puzzle.get(i).getIndex()));

        }

    }

    private ArrayList<Integer> generateIntSet(){
        ArrayList<Integer> intSet = new ArrayList<Integer>();

        while(intSet.size() != 9){
            int rand = new Random().nextInt(9);
            if(!intSet.contains(rand)){
                intSet.add(rand);
            }
        }

        return intSet;
    }

    public void printPuzzle(){
        String buffer = "";
        for(int i = 0; i < puzzle.size(); i++){
            buffer += String.valueOf(puzzle.get(i).getIndex());
            if(i == 2  || i == 5 || i == 8 ){
                buffer += "\n";
            }
        }
        System.out.println(buffer);

    }

    public boolean isFinished() {
        for (int i = 0; i < this.puzzle.size(); i++) {
            if (this.puzzle.get(i).getIndex() != i) {
                System.out.println("NOT FINISHED");
                return false;
            }

        }
        return true;
    }

    private Vector<Drawable> savingBackgrounds(){
        Vector<Drawable> backgrounds = new Vector<Drawable>();
        for(int i = 0; i < this.puzzle.size(); i ++){
                backgrounds.add(this.puzzle.get(i).getTextView().getBackground());
        }
        return backgrounds;
    }
    public void finishedMessage(MainActivity m){
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setCancelable(false);
        builder.setTitle("YOU WIN!");
        builder.setMessage("Completaste el juego\n¿Deseas comenzar de nuevo?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mixing(tvMovement);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(m,StartActivity.class);
                m.startActivity(intent);
            }
        });
        builder.show();
    }


}
