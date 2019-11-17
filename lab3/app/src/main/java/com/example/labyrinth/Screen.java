package com.example.labyrinth;

import android.app.Activity;
import android.content.Intent;
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

    int width=5;
    int height=10;

    DrawView view;
    GameLogic game;
    GameTypes.Types type;

    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.type=(GameTypes.Types)bundle.getSerializable(MainActivity.EXTRA_MESSAGE);

        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        width=displaySize.x/50; height=displaySize.y/50;
        game=new GameLogic(width,height, type);

        view=new DrawView(this, displaySize, new Size(width,height));
        setContentView(view);
        view.setGameType(type);
        startNewGame();
        SwipeGestureDetector gestureListener =new SwipeGestureDetector(this);
        gestureDetectorCompat=new GestureDetectorCompat(this, gestureListener);

    }


    public void Move(Moves.Move move){
        Point lastHeroPoint;
        do {
            lastHeroPoint=game.getHeroPoint();
            if (game.move(move)){
                openDialog();
            }//todo mb change
            view.setHero(game.getHeroPoint());
            view.invalidate();
            System.out.println(lastHeroPoint);
            System.out.println(game.heroPoint);
        }while (lastHeroPoint!=game.heroPoint&&game.couldNextMove());//todo change

        view.setHero(game.getHeroPoint());
        view.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void startNewGame() {
        game.restart();
        view.setField(game.getMatrix(), game.getFinishPoint());
        view.setHero(game.getHeroPoint());
        view.invalidate();
    }

    public void returnToMainMenu() {
        finish();//try it
    }

    public void openDialog() {
        AlertDialogWindow.showDialogWindow(this);

    }//todo

    @Override
    public void onClick(View view) { }


}
