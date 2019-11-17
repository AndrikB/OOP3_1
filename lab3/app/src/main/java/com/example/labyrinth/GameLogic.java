package com.example.labyrinth;

import android.graphics.Point;

public class GameLogic {
    GameTypes.Types type;
    Point heroPoint=new Point();
    Point finishPoint;
    int [][]matrix;
    LabyrinthGenerator l;
    public GameLogic(int width, int height, GameTypes.Types type){
        l=new LabyrinthGenerator(width,height);
        this.type=type;
    }

    public void restart(){
        heroPoint.set(1,1);
        l.generate();
        setMatrix(l.getMatrix() );
        setFinishPoint(l.getExitPoint());
    }

    public final boolean couldNextMove(){
        int countFreeCells=0;


        if (matrix[heroPoint.y+1][heroPoint.x]==0){countFreeCells++;
            System.out.print(new Point(heroPoint.x,heroPoint.y+1));
            System.out.println(matrix[heroPoint.y+1][heroPoint.x]);
        }
        if (matrix[heroPoint.y-1][heroPoint.x]==0){countFreeCells++;
            System.out.print(new Point(heroPoint.x,heroPoint.y-1));
            System.out.println(matrix[heroPoint.y-1][heroPoint.x]);
        }
        if (matrix[heroPoint.y][heroPoint.x+1]==0){countFreeCells++;
            System.out.print(new Point(heroPoint.x+1,heroPoint.y));
            System.out.println(matrix[heroPoint.y][heroPoint.x+1]);
        }
        if (matrix[heroPoint.y][heroPoint.x-1]==0){countFreeCells++;
            System.out.print(new Point(heroPoint.x-1,heroPoint.y));
            System.out.println(matrix[heroPoint.y][heroPoint.x-1]);
        }
        System.out.println(countFreeCells);
        return (countFreeCells<3&&type== GameTypes.Types.Classic);
    }

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
            return  checkWin();

            //return false;//todo mb change
        }
        return false;
    }

    public boolean checkWin(){
        return heroPoint.equals(finishPoint);
    }
}
