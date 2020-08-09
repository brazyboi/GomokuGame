package com.example.gomokugame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameSurface extends View {

    private Paint paint;
    private Bitmap blackPiece, whitePiece;
    private float x = 0;
    private float y = 0;
    Canvas canvas;
    private boolean imageShow = false;
    public int clickCount = 0;
    List<GamePiece> list = new ArrayList<>();

    public GameSurface(Context context, AttributeSet attrs){
        super(context, attrs);
        paint = new Paint();
        blackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.blackpiece);
        whitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.whitepiece);
    }

    public void drawImage(float x, float y){
        imageShow = true;
        clickCount = clickCount + 1;
        this.x = x;
        this.y = y;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
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

        }
    }

    public void save(List<GamePiece> gamePieces, GamePiece gamePiece){
        gamePieces.add(gamePiece);
    }

    public int getClickCount(){
        return clickCount;
    }

}
