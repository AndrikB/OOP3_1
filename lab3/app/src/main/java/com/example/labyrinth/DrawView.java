package com.example.labyrinth;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Size;
import android.view.View;

import static java.lang.Math.min;

class GameTypes{
    enum Types{
        Classic, Hard
    }
}


public class DrawView extends View {

    private GameTypes.Types type;
    private Point heroPoint;
    private Point finishPoint;
    private Size countCell;
    private float cellWidth, cellHeight;
    Paint paintField=new Paint();
    Paint paintHero=new Paint();
    Paint paintLight=new Paint();
    int [][] matrix;



    public DrawView(Context context, Point screenSize, Size countCell) {
        super(context);
        paintField.setColor(Color.GRAY);
        paintHero.setColor(Color.RED);
        paintLight.setColor(Color.YELLOW);
        this.setBackgroundColor(Color.BLACK);
        this.countCell=countCell;
        cellWidth=((float)screenSize.x)/countCell.getWidth();
        cellHeight=((float)screenSize.y)/countCell.getHeight();

        paintField.setStrokeWidth(3);

            System.out.println("here 0");
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(matrix.length<1||matrix[0].length<0) return;

        if(type==GameTypes.Types.Hard) {
            for (int i = 0; i < countCell.getHeight(); i++)
                for (int j = 0; j < countCell.getWidth(); j++)
                    if (Math.min(Math.pow(heroPoint.x - j, 2.0) + Math.pow(heroPoint.y - i, 2),
                            Math.pow(finishPoint.x - j, 2.0) + Math.pow(finishPoint.y - i, 2)) < 6)
                        drawRect(i, j, canvas);
        }
        else if (type==GameTypes.Types.Classic){
            for (int i = 0; i < countCell.getHeight(); i++)
                for (int j = 0; j < countCell.getWidth(); j++)
                    drawRect(i, j, canvas);
        }


    }

    private void drawRect(int i, int j, Canvas canvas){
        if (matrix[i][j]==1)
            canvas.drawRect(j*cellWidth,i*cellHeight,(j+1)*cellWidth,(i+1)*cellHeight, paintField);
        else
            canvas.drawRect(j*cellWidth,i*cellHeight,(j+1)*cellWidth,(i+1)*cellHeight, paintLight);

        if (heroPoint.equals(j,i)){
            canvas.drawCircle((j+0.5f)*cellWidth,(i+0.5f)*cellHeight,min(cellWidth, cellHeight)/2, paintHero);
        }
    }

    public void setField(int [][]matrix, Point finishPoint){
        this.matrix=matrix;
        this.finishPoint=finishPoint;
    }

    public void setHero(Point heroPoint){
        this.heroPoint=heroPoint;
    }

    public void setGameType(GameTypes.Types type){
        this.type=type;
    }

}
