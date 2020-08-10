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

public class GameBoard {

    private int space = 120;
    private int numOfPiecesPlayed;
    public String winner;
    private Bitmap blackPiece, whitePiece;
    GamePiece[][] piecesOnBoard = new GamePiece[16][9];

    public GameBoard(Bitmap whitePiece, Bitmap blackPiece) {
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
    }

    public String checkWinner(int xIndex, int yIndex){
        GamePiece gamePiece = piecesOnBoard[yIndex][xIndex];

        for (int startIndex = xIndex-4; startIndex < xIndex; startIndex++) {
            int numInRow = 0;
            for (int currentIndex = startIndex; currentIndex < 5+startIndex; currentIndex++) {
                if (currentIndex < 0 || currentIndex >= piecesOnBoard[0].length) {
                    break;
                }
                if (piecesOnBoard[yIndex][currentIndex] == null) {
                    break;
                }
                if (piecesOnBoard[yIndex][currentIndex].isWhite != gamePiece.isWhite) {
                    break;
                }
                numInRow++;
                if (numInRow == 5){
                    if (gamePiece.isWhite){
                        return "player1";
                    } else {
                        return "player2";
                    }
                }
            }
        }

        for (int startIndex = yIndex-4; startIndex<yIndex; startIndex++){
            int numInRow = 0;
            for (int currentIndex = startIndex; currentIndex < 5+startIndex; currentIndex++) {
                if (currentIndex < 0 || currentIndex >= piecesOnBoard[0].length) {
                    continue;
                }
                if (piecesOnBoard[currentIndex][xIndex] == null) {
                    break;
                }
                if (piecesOnBoard[currentIndex][xIndex].isWhite != gamePiece.isWhite) {
                    break;
                }
                numInRow++;
                if (numInRow == 5){
                    if (gamePiece.isWhite){
                        return "player1";
                    } else {
                        return "player2";
                    }
                }
            }
        }
        return "none";
    }

    public void playPiece(float x, float y) {
        GamePiece gamePiece = new GamePiece();

        numOfPiecesPlayed++;

        int xIndex = (int) ((x + space / 2) / space);
        int yIndex = (int) ((y + space / 2) / space);

        int xFinal = xIndex * space;
        int yFinal = yIndex * space;

        gamePiece.x = xFinal;
        gamePiece.y = yFinal;

        if (numOfPiecesPlayed % 2 == 0) {
            gamePiece.isWhite = false;
        } else {
            gamePiece.isWhite = true;
        }

        if (piecesOnBoard[yIndex][xIndex] != null) {

        } else {
            piecesOnBoard[yIndex][xIndex] = gamePiece;
        }

        winner = checkWinner(xIndex, yIndex);
    }


    public void drawBoard(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for (int i = 0; i < 1080 / space; i++) {
            canvas.drawLine(space * i, 0, space * i, 1920, paint);
        }
        for (int k = 0; k < 1920 / space; k++) {
            canvas.drawLine(0, space * k, 1080, space * k, paint);
        }

        //draw pieces
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                GamePiece gamePiece = piecesOnBoard[i][j];
                if (gamePiece != null) {
                    Bitmap pieceImage = gamePiece.isWhite ? whitePiece : blackPiece;
                    Rect src = new Rect(0, 0, pieceImage.getWidth(), pieceImage.getHeight());
                    int dstLeft = (int) gamePiece.x - 40;
                    int dstTop = (int) gamePiece.y - 40;
                    Rect destRectangle = new Rect(dstLeft, dstTop, 80 + dstLeft, 80 + dstTop);

                    canvas.drawBitmap(pieceImage, src, destRectangle, null);
                }
            }
        }
    }
}
