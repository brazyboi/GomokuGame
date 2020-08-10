package com.example.gomokugame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class GameBoard {

    private int space = 120;
    private int numOfPiecesPlayed;
    public String winner = "";
    private Bitmap blackPiece, whitePiece;
    GamePiece[][] piecesOnBoard = new GamePiece[16][9];

    public GameBoard(Bitmap whitePiece, Bitmap blackPiece) {
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
    }

    public boolean checkWinner(int xIndex, int yIndex, int dirX, int dirY){
        GamePiece gamePiece = piecesOnBoard[yIndex][xIndex];

        for (int i=0; i < 5; i++) {
            int startXIndex = xIndex+(i-4)*dirX;
            int startYIndex = yIndex+(i-4)*dirY;
            int numInRow = 0;
            for(int j = 0; j<5; j++) {
                int curXIndex = startXIndex + j*dirX;
                int curYIndex = startYIndex + j*dirY;
                if (curXIndex < 0 || curXIndex >= piecesOnBoard[0].length || curYIndex < 0 || curYIndex >= piecesOnBoard.length){
                    break;
                }
                GamePiece gp = piecesOnBoard[curYIndex][curXIndex];
                if(gp == null){
                    break;
                }
                if (gp.isWhite != gamePiece.isWhite){
                    break;
                }
                numInRow++;
            }
            if (numInRow == 5){
                return true;
            }
        }

        return false;
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

        if (checkWinner(xIndex, yIndex, 1, 0) || checkWinner(xIndex, yIndex, 0, 1) || checkWinner(xIndex, yIndex, 1, 1) || checkWinner(xIndex, yIndex, 1,-1)){
            if (gamePiece.isWhite){
                winner = "player1";
            } else {
                winner = "player2";
            }
        }
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
