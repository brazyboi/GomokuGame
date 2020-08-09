package com.example.gomokugame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameBoard  {

    private int space = 120;
    private Bitmap blackPiece, whitePiece;
    List<GamePiece> list = new ArrayList<>();

    public GameBoard(Bitmap whitePiece, Bitmap blackPiece) {
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
    }

    Point alignPiece(float x, float y) {

        int xIndex = (int) ((x+space/2)/space);
        int yIndex = (int) ((y+space/2)/space);

        int xFinal = xIndex*space;
        int yFinal = yIndex*space;

        return new Point(xFinal, yFinal);
    }

    public void playPiece(float x, float y) {
        GamePiece gamePiece = new GamePiece();

        Point point = alignPiece(x, y);

        gamePiece.x = point.x;
        gamePiece.y = point.y;

        if (list.size() % 2 == 0) {
            gamePiece.isWhite = true;
        } else {
            gamePiece.isWhite = false;
        }
        list.add(gamePiece);
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

        //draw pieces
        for (int i = 0; i < list.size(); i++) {
            GamePiece gamePiece = list.get(i);
            Bitmap pieceImage = gamePiece.isWhite?whitePiece:blackPiece;
            Rect src = new Rect(0, 0, pieceImage.getWidth(), pieceImage.getHeight());
            int dstLeft = (int) gamePiece.x - 40;
            int dstTop = (int) gamePiece.y - 40;
            Rect destRectangle = new Rect(dstLeft, dstTop, 80+dstLeft, 80 + dstTop);

            canvas.drawBitmap(pieceImage, src, destRectangle, null);

        }
    }
}
