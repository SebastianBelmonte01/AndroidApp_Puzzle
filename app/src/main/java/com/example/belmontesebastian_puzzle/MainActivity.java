package com.example.belmontesebastian_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv0 = findViewById(R.id.tvFirst);
        tv1 = findViewById(R.id.tvSecond);
        tv2 = findViewById(R.id.tvThird);
        tv3 = findViewById(R.id.tvFourth);
        tv4 = findViewById(R.id.tvFifth);
        tv5 = findViewById(R.id.tvSixth);
        tv6 = findViewById(R.id.tvSeventh);
        tv7 = findViewById(R.id.tvEighth);
        tv8 = findViewById(R.id.tvNinth);


        Piece piece0 = new Piece(0,tv0);
        Piece piece1 = new Piece(1,tv1);
        Piece piece2 = new Piece(2,tv2);
        Piece piece3 = new Piece(3,tv3);
        Piece piece4 = new Piece(4,tv4);
        Piece piece5 = new Piece(5,tv5);
        Piece piece6 = new Piece(6,tv6);
        Piece piece7 = new Piece(7,tv7);
        Piece piece8 = new Piece(8,tv8);

        Vector<Piece> pieces = new Vector<Piece>();
        pieces.add(piece0);
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        pieces.add(piece4);
        pieces.add(piece5);
        pieces.add(piece6);
        pieces.add(piece7);
        pieces.add(piece8);

        Puzzle initialPuzzle = new Puzzle(pieces);
        initialPuzzle.printPuzzle();
        initialPuzzle.clickReceived();



    }
}