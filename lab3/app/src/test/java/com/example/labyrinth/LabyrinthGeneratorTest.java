package com.example.labyrinth;


import android.graphics.Point;

import org.junit.Test;

import static org.junit.Assert.*;

class LabyrinthGeneratorTest {

    private LabyrinthGenerator generatorSeeded0;
    private LabyrinthGenerator generatorSeeded1;
    private LabyrinthGenerator generatorSeeded2;
    private LabyrinthGenerator generatorSeeded3;
    private LabyrinthGenerator generatorSeeded4;

    {
        int x = 10;
        int y = 10;
        generatorSeeded0 = new LabyrinthGenerator(x, y, 5555);
        generatorSeeded1 = new LabyrinthGenerator(x, y, 1000);
        generatorSeeded2 = new LabyrinthGenerator(x, y, 2000);
        generatorSeeded3 = new LabyrinthGenerator(x * 2, y * 2, 9999);
        generatorSeeded4 = new LabyrinthGenerator(x * 2, y * 2, 9);
    }

    @Test
    void getMatrix() {
        int[][] matrix = generatorSeeded0.getMatrix();
        assertEquals(matrix.length, 10);
        assertEquals(matrix[0].length, 10);
    }

    @Test
    void getExitPoint() {
        assertEquals(generatorSeeded0.getExitPoint(), new Point(9,1));
        assertEquals(generatorSeeded1.getExitPoint(), new Point(4,0));
        assertEquals(generatorSeeded2.getExitPoint(), new Point(0,3));
        assertEquals(generatorSeeded3.getExitPoint(), new Point(6,19));
        assertEquals(generatorSeeded4.getExitPoint(), new Point(19,3));
    }


}