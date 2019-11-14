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

    int width=20;
    int height=40;

    DrawView view;
    GameLogic game;
    GameTypes.Types type;

    private GestureDetectorCompat gestureDetectorCompat;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get the Intent that started this activity and extract the string
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.type=(GameTypes.Types)bundle.getSerializable(MainActivity.EXTRA_MESSAGE);
        game=new GameLogic( );

        LabyrinthGenerator l=new LabyrinthGenerator(width,height);
        game.setMatrix(l.getMatrix() );
        game.setFinishPoint(l.getExitPoint());

        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);

        view=new DrawView(this, displaySize, new Size(width,height));
        setContentView(view);

        view.setGameType(type);
        view.setField(game.getMatrix(), game.getFinishPoint());
        view.setHero(game.getHeroPoint());
        view.invalidate();

        SwipeGestureDetector gestureListener =new SwipeGestureDetector(this);
        gestureDetectorCompat=new GestureDetectorCompat(this, gestureListener);

    }

    public void Move(Moves.Move move){
        while (game.move(move)){};//todo mb change
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
