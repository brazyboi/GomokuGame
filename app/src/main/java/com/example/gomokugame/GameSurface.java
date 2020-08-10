package com.example.gomokugame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GameSurface extends View {

    private GameBoard board;

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bitmap blackPiece, whitePiece;
        blackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.blackpiece);
        whitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.whitepiece);
        board = new GameBoard(whitePiece, blackPiece);
    }

    public void playPiece(float x, float y){
        board.playPiece(x, y);
        invalidate();
    }

    public String getWinner(){
        return board.winner;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.drawBoard(canvas);

    }

}
