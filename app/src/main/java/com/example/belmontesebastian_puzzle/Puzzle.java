package com.example.belmontesebastian_puzzle;

import android.view.View;

import java.util.Vector;

public class Puzzle {
    public Vector<Piece> puzzle;

    public Puzzle(Vector<Piece> puzzle) {
        this.puzzle = puzzle;
    }

    public Vector<Piece> getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Vector<Piece> puzzle) {
        this.puzzle = puzzle;
    }

    public void clickReceived() {
        for(int i = 0; i < this.puzzle.size(); i++) {
            final int ii = i;
            puzzle.get(i).getTextView().setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                   Piece clickedPiece = puzzle.get(ii);
                   System.out.println("Click in piece with index: " + clickedPiece.getIndex());
                   System.out.println(ii);
                   if((ii < 2  || ii < 5 || ii < 8) && puzzle.get(ii + 1).getTextView().getText().toString().length() == 0){
                       System.out.println("El de mi derecha es vacio");
                       puzzle.get(ii).exchangedPieces(ii, ii+1,puzzle);
                   }
                   else{
                       if((ii > 0 || ii > 3 || ii > 6) && puzzle.get(ii - 1).getTextView().getText().toString().length() == 0){
                           System.out.println("El de mi izquierda es vacio");
                           puzzle.get(ii).exchangedPieces(ii, ii-1,puzzle);
                       }
                       else{
                           if(ii - 3 > 0 && puzzle.get(ii - 3).getTextView().getText().length() == 0){
                               puzzle.get(ii).exchangedPieces(ii, ii-3,puzzle);
                           }
                           else if(ii + 3 > 0 && puzzle.get(ii + 3).getTextView().getText().length() == 0){
                               puzzle.get(ii).exchangedPieces(ii, ii+3,puzzle);

                           }
                       }
                   }
                   printPuzzle();
               }

            });

        }

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
}
