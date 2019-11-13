package com.example.labyrinth;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.core.view.GestureDetectorCompat;

public class Screen extends Activity
        implements View.OnClickListener{


    Point displaySize=new Point();

    int width=20;
    int height=40;

    DrawView view;
    GameLogic game;

    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        game=new GameLogic();

        LabyrinthGenerator l=new LabyrinthGenerator(width,height);
        game.setMatrix(l.getMatrix() );


        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        view=new DrawView(this, displaySize, new Size(width,height));

        setContentView(view);
        view.setField(game.getMatrix());
        view.setHero(game.getHeroPoint());
        view.invalidate();

        SwipeGestureDetector gestureListener =new SwipeGestureDetector(this);
        gestureDetectorCompat=new GestureDetectorCompat(this, gestureListener);

    }

    public void Move(Moves.Move move){
        game.move(move);
        view.setHero(game.getHeroPoint());
        view.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    @Override
    public void onClick(View view) {

    }


}
