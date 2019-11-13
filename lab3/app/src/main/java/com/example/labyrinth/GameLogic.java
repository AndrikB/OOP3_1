package com.example.labyrinth;

import android.graphics.Point;

public class GameLogic {

    Point heroPoint=new Point(1,1);
    int [][]matrix;

    public void setMatrix(int [][]matrix){
        this.matrix=matrix;
    }
    public int [][] getMatrix(){
        return matrix;
    }

    public Point getHeroPoint(){
        return heroPoint;
    }

    public void move(Moves.Move move){
        Point nextItem=new Point(heroPoint);
        if (move == Moves.Move.RIGHT)nextItem.x++;
        if (move == Moves.Move.LEFT)nextItem.x--;
 
        if (move == Moves.Move.DOWN)nextItem.y++;
        if (move == Moves.Move.UP)nextItem.y--;
        if (matrix[nextItem.y][nextItem.x]==0) heroPoint=nextItem;

    }
}
