package com.example.labyrinth;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Size;
import android.view.View;

import static java.lang.Math.min;

public class DrawView extends View {

    private final Point screenSize;
    private Point heroPoint;
    private Size countCell;
    private float cellWidth, cellHeight;
    Paint paintField=new Paint();
    Paint paintHero=new Paint();
    int [][] matrix;

    public DrawView(Context context, Point screenSize, Size countCell) {
        super(context);
        paintField.setColor(Color.BLACK);
        paintHero.setColor(Color.RED);

        this.screenSize =screenSize;
        this.countCell=countCell;
        cellWidth=((float)screenSize.x)/countCell.getWidth();
        cellHeight=((float)screenSize.y)/countCell.getHeight();

        paintField.setStrokeWidth(3);

            System.out.println("here 0");
    }

    @Override
    public void onDraw(Canvas canvas) {
        System.out.println("here");
        if(matrix.length<1||matrix[0].length<0) {
            canvas.drawLine(0,0,100,100,paintField);
            System.out.println("here 2");
            return;
        }
        System.out.println("here 1");
        for (int i=0;i<countCell.getHeight();i++){

            for (int j=0;j<countCell.getWidth();j++){
                if (heroPoint.equals(j,i)){
                    canvas.drawCircle((j+0.5f)*cellWidth,(i+0.5f)*cellHeight,min(cellWidth, cellHeight)/2, paintHero);
                }
                if (matrix[i][j]==1){
                    canvas.drawRect(j*cellWidth,i*cellHeight,(j+1)*cellWidth,(i+1)*cellHeight, paintField);
                }
            }


        }



    }

    public void setField(int [][]matrix){
        this.matrix=matrix;
    }

    public void setHero(Point heroPoint){
        this.heroPoint=heroPoint;
    }
}
