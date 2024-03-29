package com.example.labyrinth;

import android.view.GestureDetector;
import android.view.MotionEvent;

import static java.lang.StrictMath.max;


public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 50;
    private static final int SWIPE_MAX_OFF_PATH = 200;

    private Screen activity;

    SwipeGestureDetector(Screen activity){
        this.activity=activity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
        try {

            float deltaY = e2.getY() - e1.getY();
            float deltaX = e2.getX() - e1.getX();

            float deltaYAbs = Math.abs(deltaY);
            float deltaXAbs = Math.abs(deltaX);
            if (max(deltaXAbs,deltaYAbs) < SWIPE_MIN_DISTANCE)
                return false;

            if (deltaXAbs>deltaYAbs){
                if (deltaX>0) activity.Move(Move.RIGHT);
                else activity.Move(Move.LEFT);
            }
            else{
                if (deltaY>0) activity.Move(Move.DOWN);
                else activity.Move(Move.UP);
            }

        } catch (Exception e) {
            // Log.e("Home", "Error on gestures");
        }
        return false;
    }
}
