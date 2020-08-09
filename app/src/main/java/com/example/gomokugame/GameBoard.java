package com.example.gomokugame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard  {

    private int space = 120;
    public GameBoard() {

    }

    Point alignPiece(float x, float y) {

        int xIndex = (int) ((x+space/2)/space);
        int yIndex = (int) ((y+space/2)/space);

        int xFinal = xIndex*space;
        int yFinal = yIndex*space;

        return new Point(xFinal, yFinal);

    }

    public void drawBoard(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for(int i = 0; i<9; i++) {
            canvas.drawLine(space*i, 0, space*i, 1920, paint);
        }
        for(int k =0; k<16; k++){
            canvas.drawLine(0,space*k,1080,space*k,paint);
        }
    }
}
