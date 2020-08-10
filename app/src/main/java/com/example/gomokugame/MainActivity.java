package com.example.gomokugame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private float x;
    private float y;
    private GameSurface gameSurface;
    private GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameSurface = (GameSurface) findViewById(R.id.game_surface);
        final TextView textView1 = (TextView) findViewById(R.id.text_game_over);
        final TextView textView2 = (TextView) findViewById(R.id.text_winner);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);

        gameSurface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    gameSurface.playPiece(x, y);

                    if (gameSurface.getWinner().equals("player1")) {
                        textView2.setText("Player 1 wins!");
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                    } else if (gameSurface.getWinner().equals("player2")){
                        textView2.setText("Player 2 wins!");
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                    }

                }
                return false;
            }
        });

    }
}