package com.example.labyrinth;

import android.graphics.Point;

public class GameLogic {

    Point heroPoint=new Point(1,1);
    Point finishPoint;
    int [][]matrix;



    public void setMatrix(int [][]matrix){
        this.matrix=matrix;
    }
    public int [][] getMatrix(){
        return matrix;
    }

    public void setFinishPoint(Point finishPoint){
        this.finishPoint=finishPoint;
    }

    public Point getFinishPoint(){return finishPoint;}

    public Point getHeroPoint(){
        return heroPoint;
    }

    public boolean move(Moves.Move move){
        Point nextItem=new Point(heroPoint);
        if (move == Moves.Move.RIGHT)nextItem.x++;
        if (move == Moves.Move.LEFT)nextItem.x--;
        if (move == Moves.Move.DOWN)nextItem.y++;
        if (move == Moves.Move.UP)nextItem.y--;
        if (matrix[nextItem.y][nextItem.x]==0) {
            heroPoint=nextItem;
            return false;//todo mb change
        }
        return false;
    }
}
