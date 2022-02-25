package com.example.belmontesebastian_puzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    TextView tvMov;
    Button btnExit, btnMix, btnNew;
    String currentUrl;
    Bitmap img;
    ImageView imgView;
    RequestQueue queue;
    Vector<Piece> pieces = new Vector<Piece>();
    Puzzle initialPuzzle;




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
        tvMov = findViewById(R.id.tvMov);
        btnExit = findViewById(R.id.btnSalir);
        btnMix = findViewById(R.id.btnMezclar);
        btnNew = findViewById(R.id.btnNuevo);

        imgView = findViewById(R.id.ivPic);







        Piece piece0 = new Piece(0,tv0);
        Piece piece1 = new Piece(1,tv1);
        Piece piece2 = new Piece(2,tv2);
        Piece piece3 = new Piece(3,tv3);
        Piece piece4 = new Piece(4,tv4);
        Piece piece5 = new Piece(5,tv5);
        Piece piece6 = new Piece(6,tv6);
        Piece piece7 = new Piece(7,tv7);
        Piece piece8 = new Piece(8,tv8);

        pieces.add(piece0);
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        pieces.add(piece4);
        pieces.add(piece5);
        pieces.add(piece6);
        pieces.add(piece7);
        pieces.add(piece8);

        queue = Volley.newRequestQueue(this);

        queue.add(requestImage());




        /*
        Bitmap squareImage =  Bitmap.createBitmap(img, 250, 250, 250, 250);
        Drawable d = new BitmapDrawable(Resources.getSystem(),squareImage);
        imgView.setBackground(d);
         */


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        btnMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialPuzzle.mixing(tvMov);
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public ImageRequest requestImage(){

        currentUrl = "https://picsum.photos/750";

        ImageRequest req = new ImageRequest(
                currentUrl,
                new Response.Listener<Bitmap>(){
                    @Override
                     public void onResponse(Bitmap response){
                        System.out.println(response.toString());
                        img = response;
                        System.out.println("IMGA: " + img.getHeight() + " " + img.getWidth());

                        Drawable background = new BitmapDrawable(getResources(),img);
                        imgView.setBackground(background);
                        cutImage(img);

                        initialPuzzle = new Puzzle(pieces);
                        initialPuzzle.mixing(tvMov);
                        initialPuzzle.clickReceived(tvMov, MainActivity.this);
                    }
                },
                750,
                750,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("aa","Error"+error.getMessage());
                    }
                }

        );
        req.setShouldCache(false);
        return req;
    }
    public void cutImage(Bitmap background) {
        int y = 0;
        for (int i = 0; i < 3; i++) {
            int x = 0;
            for(int j = 0; j < 3; j++){
                Piece current = pieces.get(( 3 * i ) + j);
                current.getTextView().setWidth(250);
                current.getTextView().setHeight(250);
                if(current.getIndex() != 8){
                    Bitmap squareImage =  Bitmap.createBitmap(background, x, y, 250, 250);
                    Drawable d = new BitmapDrawable(getResources(),squareImage);
                    System.out.println("D: " + d);

                    current.getTextView().setBackground(d);
                }
                x += 250;
            }
            y += 250;
        }
    }



}
