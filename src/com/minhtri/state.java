package com.minhtri;

import java.io.FileNotFoundException;

/**
 * Created by le on 4/12/2015.
 */
public class state {
    public String filename;

    public boolean equal(state other) {
        return false;
    }

    public boolean iswin() {
        return false;
    }

    public int[][] readmatrix() throws FileNotFoundException {
        return new int[1][1];
    }

    public void resumeGame() throws FileNotFoundException {

    }


}
