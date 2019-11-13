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
    Point heroPoint=new Point(1,1);
    int width=20;
    int height=40;

    DrawView view;
    int [][]matrix;


    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LabyrinthGenerator l=new LabyrinthGenerator(width,height);
        matrix=l.getMatrix();


        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        view=new DrawView(this, displaySize, new Size(width,height));

        setContentView(view);
        view.setField(matrix);
        view.setHero(heroPoint);
        view.invalidate();

        SwipeGestureDetector gestureLisener =new SwipeGestureDetector(this);
        gestureDetectorCompat=new GestureDetectorCompat(this, gestureLisener);

    }

    public void Move(Moves.Move move){
        if (move== Moves.Move.RIGHT)heroPoint.x++;
        if (move== Moves.Move.LEFT)heroPoint.x--;

        if (move== Moves.Move.DOWN)heroPoint.y++;
        if (move== Moves.Move.UP)heroPoint.y--;
        view.setHero(heroPoint);
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
