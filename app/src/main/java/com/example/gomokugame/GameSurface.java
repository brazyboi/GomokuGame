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

    private Paint paint;
    private Bitmap blackPiece, whitePiece;
    List<GamePiece> list = new ArrayList<>();
    private GameBoard board = new GameBoard();

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        blackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.blackpiece);
        whitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.whitepiece);
    }

    public void playPiece(float x, float y) {
        GamePiece gamePiece = new GamePiece();

        Point point = board.alignPiece(x, y);

        gamePiece.x = point.x;
        gamePiece.y = point.y;

        if (list.size() % 2 == 0) {
            gamePiece.isWhite = true;
            gamePiece.color = whitePiece;
        } else {
            gamePiece.isWhite = false;
            gamePiece.color = blackPiece;
        }

        list.add(gamePiece);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(imageShow) {
            GamePiece gamePiece = new GamePiece();
            gamePiece.x = x;
            gamePiece.y = y;
            if(clickCount%2 == 0) {
                canvas.drawBitmap(whitePiece, x, y, null);
                gamePiece.color = whitePiece;
            } else {
                canvas.drawBitmap(blackPiece, x, y, null);
                gamePiece.color = blackPiece;
            }
            save(list, gamePiece);
            for (int i = 1;i<clickCount+1;i++){
                canvas.drawBitmap(list.get(clickCount-i).color, list.get(clickCount-i).x, list.get(clickCount-i).y, null);
            }

            canvas.drawBitmap(gamePiece.color, src, destRectangle, null);

        }
    }

}
