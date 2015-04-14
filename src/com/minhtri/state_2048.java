package com.minhtri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by le on 3/23/2015.
 */
public class state_2048 extends state {

    public int[][] matrix = new int[4][4];
    private int nFreeTile = 16;
    private ArrayList<indexTile> listFreeTiles = new ArrayList<indexTile>();
    public ArrayList<state_2048> listNextState = new ArrayList<state_2048>();
    public int goal = 64;
    public state_2048 parent;

    public int getnFreeTile() {
        calFreeTile(matrix);
        return nFreeTile;
    }

    public void setGoal(int input) {
        goal = input;
    }


    public ArrayList<state_2048> getListNextStateBRFS() {
        nextStateBRFS();
        return listNextState;
    }

    public ArrayList<state_2048> getListNextStateDFS() {
        nextStateDFS();
        return listNextState;
    }

    public boolean equal(state_2048 other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] != other.matrix[i][j]) return false;
            }
        }
        return true;
    }

    /*
     get matrix of state_2048
     */
    public int[][] getmatrix() {
        return matrix;
    }

    /*
    * game start from initial state_2048
    * all tiles are free
    *
    * */
    public state_2048(int _goal) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = 0;
            }
        }
        goal = _goal;
        nFreeTile = 16;
        parent = null;
        this.filename = "2048.txt";
    }


    public state_2048(int[][] input, state_2048 _parent) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(input[i], 0, matrix[i], 0, 4);
        }
        calFreeTile(matrix);
        parent = _parent;
    }

    /*
    * game start from a state_2048 stored in file
    * */
    public void resumeGame() throws FileNotFoundException {
        int[][] result = readmatrix();
        for (int i = 0; i < 4; i++) {
            System.arraycopy(result[i], 0, matrix[i], 0, 4);
        }
    }


    /*
    * read file data to matrix
    * */
    public int[][] readmatrix() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("./" + filename));

        //matrix contain begin state_2048 of game
        int[][] matrix = new int[4][4];

        int row = 0;

        // read begin state_2048 from file to matrix
        while (scanner.hasNextLine()) {

            int collum = 0;

            String line = scanner.nextLine();

            Scanner scannerline = new Scanner(line);

            scannerline.useDelimiter(" ");

            while (scannerline.hasNextInt()) {

                matrix[row][collum] = scannerline.nextInt();

                collum++;
            }

            row++;
        }

        return matrix;
    }

    /*
    * genertate random value
    * */

    public int randomvalue() {
        return Math.random() < 0.8 ? 2 : 4;
    }

    /*
    * choose free tile to fill random value
    * */

    public indexTile randomIndex(ArrayList<indexTile> input) {
        Random myrandom = new Random();
        int index = myrandom.nextInt(input.size());
        return input.get(index);
    }


    /*
    * calculate the number of tile free and index of free tile
    * */
    private void calFreeTile(int[][] matrix) {
        int nFTile = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] == 0) {
                    nFTile++;
                    listFreeTiles.add(new indexTile(i, j));
                }
            }
        }
        nFreeTile = nFTile;
    }


    private ArrayList<indexTile> indexFreetile(int[][] matrix) {
        ArrayList<indexTile> listFreeTiles = new ArrayList<indexTile>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] == 0) listFreeTiles.add(new indexTile(i, j));
            }
        }
        return listFreeTiles;
    }

    /*
    * check if the player is win
    * */
    public boolean isWin() {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] > max) max = matrix[i][j];
            }
        }
        if (max == goal) return true;
        return false;
    }

    public static void copyMatrix(int[][] source, int[][] destination) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, 4);
        }
    }

    /*
    * slide array to the right
    * */
    public static int[][] slideright(int[][] temp) {

        int[][] matrix = new int[4][4];

        copyMatrix(temp, matrix);

        for (int r = 0; r < 4; r++) {
            Stack<Integer> stack = new Stack<Integer>();
            int newPos = 3;
            for (int i = 3; i >= 0; i--) {
                if (matrix[r][i] != 0) {
                    stack.push(matrix[r][i]);
                    matrix[r][i] = 0;
                    if (stack.size() == 2) {
                        int last = stack.pop();
                        int first = stack.pop();
                        if (first == last) {       // calculate the sum of two tile equal and move to the new position
                            matrix[r][newPos] = first + last;  // sum two tile and put result to new position
                            newPos--;  // new position slide to the left 1 unit
                        } else {        // move the right most tile to new position
                            stack.push(last);
                            matrix[r][newPos] = first;
                            newPos--;
                        }

                    }
                }
            }
            while (!stack.empty()) {
                matrix[r][newPos] = stack.pop();
                newPos--;
            }
        }
        return matrix;
    }

    /*
   * slide array to the left
   * */
    public static int[][] slideleft(int[][] temp) {

        int[][] matrix = new int[4][4];

        copyMatrix(temp, matrix);

        for (int r = 0; r < 4; r++) {
            Stack<Integer> stack = new Stack<Integer>();
            int newPos = 0;
            for (int i = 0; i <= 3; i++) {
                if (matrix[r][i] != 0) {
                    stack.push(matrix[r][i]);
                    matrix[r][i] = 0;
                    if (stack.size() == 2) {
                        int last = stack.pop();
                        int first = stack.pop();
                        if (first == last) {    // calculate the sum of two tile equal and move to the new position
                            matrix[r][newPos] = first + last;
                            newPos++;
                        } else {                // move the left most tile to new position
                            stack.push(last);
                            matrix[r][newPos] = first;
                            newPos++;
                        }

                    }
                }
            }
            while (!stack.empty()) {
                matrix[r][newPos] = stack.pop();
                newPos++;
            }
        }
        return matrix;
    }

    /*
    * slide array to the top
    * */
    public static int[][] slidetop(int[][] temp) {

        int[][] matrix = new int[4][4];

        copyMatrix(temp, matrix);

        for (int c = 0; c < 4; c++) {
            Stack<Integer> stack = new Stack<Integer>();
            int newPos = 0;
            for (int i = 0; i <= 3; i++) {
                if (matrix[i][c] != 0) {
                    stack.push(matrix[i][c]);
                    matrix[i][c] = 0;
                    if (stack.size() == 2) {
                        int last = stack.pop();
                        int first = stack.pop();
                        if (first == last) {            // calculate the sum of two tile equal and move to the new position
                            matrix[newPos][c] = first + last;
                            newPos++;
                        } else {                          // move the top tile to new position
                            stack.push(last);
                            matrix[newPos][c] = first;
                            newPos++;
                        }

                    }
                }
            }
            while (!stack.empty()) {
                matrix[newPos][c] = stack.pop();
                newPos++;
            }
        }
        return matrix;
    }

    /*
    * slide array to the bottom
    * */
    public static int[][] slidebottom(int[][] temp) {

        int[][] matrix = new int[4][4];

        copyMatrix(temp, matrix);

        for (int c = 0; c < 4; c++) {
            Stack<Integer> stack = new Stack<Integer>();
            int newPos = 3;
            for (int i = 3; i >= 0; i--) {
                if (matrix[i][c] != 0) {
                    stack.push(matrix[i][c]);
                    matrix[i][c] = 0;
                    if (stack.size() == 2) {
                        int last = stack.pop();
                        int first = stack.pop();
                        if (first == last) {               // calculate the sum of two tile equal and move to the new position
                            matrix[newPos][c] = first + last;
                            newPos--;
                        } else {                            // move the bottom tile to new position
                            stack.push(last);
                            matrix[newPos][c] = first;
                            newPos--;
                        }

                    }
                }
            }
            while (!stack.empty()) {
                matrix[newPos][c] = stack.pop();
                newPos--;
            }
        }
        return matrix;
    }

    /*
    * generate all next state_2048 possile for the current state_2048
     */
    public void nextStateDFS() {
        listNextState.clear();

        //slide to the right
        int[][] slideRight = slideright(matrix);
        ArrayList<indexTile> freetileright = indexFreetile(slideRight);
        if (freetileright.size() > 0) {
            indexTile index = randomIndex(freetileright);
            int ranvalue = randomvalue();

            slideRight[index.x()][index.y()] = ranvalue;

            listNextState.add(new state_2048(slideRight, this));
        }

        //slide to the left

        int[][] slideLeft = slideleft(matrix);
        ArrayList<indexTile> freetileleft = indexFreetile(slideLeft);
        if (freetileleft.size() > 0) {
            indexTile index = randomIndex(freetileleft);
            int ranvalue = randomvalue();

            slideLeft[index.x()][index.y()] = ranvalue;

            listNextState.add(new state_2048(slideLeft, this));
        }
        //slide to top

        int[][] slideTop = slidetop(matrix);
        ArrayList<indexTile> freetiletop = indexFreetile(slideTop);
        if (freetiletop.size() > 0) {
            indexTile index = randomIndex(freetiletop);
            int ranvalue = randomvalue();

            slideTop[index.x()][index.y()] = ranvalue;

            listNextState.add(new state_2048(slideTop, this));
        }

        //slide to bottom

        int[][] slideBottom = slidebottom(matrix);
        ArrayList<indexTile> freetilebottom = indexFreetile(slideBottom);
        if (freetilebottom.size() > 0) {
            indexTile index = randomIndex(freetilebottom);
            int ranvalue = randomvalue();

            slideBottom[index.x()][index.y()] = ranvalue;

            listNextState.add(new state_2048(slideBottom, this));
        }
    }


    /*
    * fill free tile with 2 or 4 , and add each state_2048 to arraylist
    * */
    public void nextStateBRFS() {
        listNextState.clear();

        // slide to the right
        int[][] slideright = slideright(matrix);
        ArrayList<indexTile> freetileright = indexFreetile(slideright);

        for (indexTile index : freetileright) {
            slideright[index.x()][index.y()] = 2;
            listNextState.add(new state_2048(slideright, this));
            slideright[index.x()][index.y()] = 0;

            slideright[index.x()][index.y()] = 4;
            listNextState.add(new state_2048(slideright, this));
            slideright[index.x()][index.y()] = 0;
        }

        // slide to the left
        int[][] slideleft = slideleft(matrix);
        ArrayList<indexTile> freetileleft = indexFreetile(slideleft);

        for (indexTile index : freetileleft) {
            slideleft[index.x()][index.y()] = 2;
            listNextState.add(new state_2048(slideleft, this));
            slideleft[index.x()][index.y()] = 0;

            slideleft[index.x()][index.y()] = 4;
            listNextState.add(new state_2048(slideleft, this));
            slideleft[index.x()][index.y()] = 0;
        }

        // slide top
        int[][] slidetop = slidetop(matrix);
        ArrayList<indexTile> freetiletop = indexFreetile(slidetop);

        for (indexTile index : freetiletop) {
            slidetop[index.x()][index.y()] = 2;
            listNextState.add(new state_2048(slidetop, this));
            slidetop[index.x()][index.y()] = 0;

            slidetop[index.x()][index.y()] = 4;
            listNextState.add(new state_2048(slidetop, this));
            slidetop[index.x()][index.y()] = 0;
        }

        // slide bottom
        int[][] slidebottom = slidebottom(matrix);
        ArrayList<indexTile> freetilebottom = indexFreetile(slidebottom);

        for (indexTile index : freetilebottom) {
            slidebottom[index.x()][index.y()] = 2;
            listNextState.add(new state_2048(slidebottom, this));
            slidebottom[index.x()][index.y()] = 0;

            slidebottom[index.x()][index.y()] = 4;
            listNextState.add(new state_2048(slidebottom, this));
            slidebottom[index.x()][index.y()] = 0;
        }


    }

}

