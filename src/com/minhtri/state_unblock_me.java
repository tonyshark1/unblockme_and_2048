package com.minhtri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by le on 3/26/2015.
 */
public class state_unblock_me extends state {

    ArrayList<bar> listBar;

    char[] listLetter;

    int cost;

    int indexChar;

    int width = 6;

    int height = 6;

    state_unblock_me parent;

    char[][] board = new char[width][height];

    bar redbar = null;


    public int[][] readmatrix() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./" + filename));

        //matrix contain begin state_unblock me of game
        int[][] matrix = new int[15][5];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = -1;
            }
        }

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


    public void resumeGame() throws FileNotFoundException {
        int[][] result = readmatrix();
        for (int i = 0; i < 15; i++) {
            if (result[i][0] != -1) {
                boolean vertical = true;
                if (result[i][0] == 0) {
                    vertical = false;
                }
                boolean special = false;
                if (result[i][4] == 1) {
                    special = true;
                }
                addBar(new bar(vertical, result[i][1], result[i][2], result[i][3], special));
            } else {
                break;
            }

        }
    }

    //initial state with a board contain all space character
    public state_unblock_me() {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = ' ';
            }
        }

        indexChar = 0;

        listLetter = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u'};

        listBar = new ArrayList<bar>();

        filename = "unblockme.txt";
    }

    //tinh diem cua trang thai hien tai
    public int score() {

        int posx = 2;
        int posy = 0;

        for (bar b : listBar) {
            if (b.isSpecial()) {
                posx = b.getX();
                posy = b.getY();
            }
        }
        ArrayList<bar> listbarchan = new ArrayList<bar>();
        int poscost = 5 - posy;
        int chancost = 0;
        for (int i = posy + 1; i < 6; i++) {
            if (board[2][i] != ' ') {
                chancost++;
                for (bar b : listBar) {
                    if (b.getLetter() == board[2][i]) {
                        listbarchan.add(b);
                    }
                }
            }
        }

        for (bar b : listbarchan) {
            int bx = b.getX();
            int by = b.getY();
            int length = b.getLength();
            if (b.getOrientation()) {
                for (int i = by + length; i < 6; i++) {
                    if (board[i][bx] != ' ') {
                        chancost++;
                    }
                }
                for (int i = by - 1; i >= 0; i--) {
                    if (board[i][bx] != ' ') {
                        chancost++;
                    }
                }

            } else {
                for (int i = bx + length; i < 6; i++) {
                    if (board[by][i] != ' ') {
                        chancost--;
                    }
                }
                for (int i = by; i >= 0; i--) {
                    if (board[by][i] != ' ') {
                        chancost--;
                    }
                }
            }
        }


        cost = poscost + chancost;
        return cost;
    }

    //check if two state is identity
    public boolean equal(state_unblock_me input) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(!(input.board[i][j] == board[i][j])) return false;
            }
        }
        return true;
    }

    //add new bar to listbar
    public void addBar(bar _newbar){
        char letter;
        if (_newbar.isSpecial()) {
            letter = '#';
            _newbar.setLetter(letter);
            redbar = _newbar;
            listBar.add(_newbar);
        } else {
            letter = listLetter[indexChar];
            _newbar.setLetter(letter);
            listBar.add(_newbar);
        }

        int x = _newbar.getX();
        int y = _newbar.getY();

        int length = _newbar.getLength();

        boolean isVertical = _newbar.getOrientation();

        indexChar++;

        if (isVertical) {
            for (int i = 0; i < length; i++) {
                board[y+i][x] = letter;
            }
        } else {
            for (int i = 0; i < length; i++) {
                board[y][x+i] = letter;
            }
        }
    }

    //is final step
    public boolean iswin() {
        if (redbar != null) {

            int x = redbar.getX();
            int y = redbar.getY();

            int length = redbar.getLength();

            for (int i = x + length ; i < width; i++) {
                if (board[y][i] != ' ') return false;
            }
        }else{
            return false;
        }
        return true;
    }

    public boolean canMove(bar _bar, int direction){

        int x = _bar.getX();
        int y = _bar.getY();

        int length = _bar.getLength();

        boolean isVertical = _bar.getOrientation();
        //direction == 1 up
        //direction == 2 down
        //direction == 3 left
        //direction == 4 right
        if(isVertical){
            if(direction == 1){
                if(y == 0) return false;
                if(board[y - 1][x] == ' ') return true; else return false;
            }else if(direction == 2){
                if (y + length > height - 1) return false;
                if(board[y+length][x] == ' ') return true; else return false;
            }
        }else{
            if(direction == 3){
                if(x == 0) return  false;
                if(board[y][x-1] == ' ') return true; else return false;
            }else if(direction == 4){
                if (x + length > width - 1) return false;
                if(board[y][x + length] == ' ') return true; else return false;
            }
        }
        return false;
    }

    public void move(int x,int y,int direction){
        //direction == 1 up
        //direction == 2 down
        //direction == 3 left
        //direction == 4 right
        char letter;
        int length;
        for(bar _bar:listBar){
            if(_bar.getX() == x && _bar.getY() == y){
                letter = _bar.getLetter();
                length = _bar.getLength();

                if(direction == 1){
                    board[y - 1][x] = letter;
                    board[y + length - 1][x] = ' ';
                    _bar.setPos(x,y-1);
                }

                if(direction == 2){
                    board[y][x] = ' ';
                    board[y + length][x] = letter;
                    _bar.setPos(x,y+1);
                }

                if(direction == 3){
                    board[y][x - 1] = letter;
                    board[y][x + length - 1] = ' ';
                    _bar.setPos(x - 1,y);
                }

                if(direction == 4){
                    board[y][x] = ' ';
                    board[y][x + length] = letter;
                    _bar.setPos(x + 1,y);
                }

            }
        }
    }

    public ArrayList<state_unblock_me> allNextState() {

        ArrayList<state_unblock_me> listPossibleState = new ArrayList<state_unblock_me>();

        for(bar _bar:listBar){
            if(canMove(_bar,1)){
                state_unblock_me a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,1);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,2)){
                state_unblock_me a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,2);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,3)){
                state_unblock_me a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,3);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,4)){
                state_unblock_me a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,4);
                a.parent = this;
                listPossibleState.add(a);
            }
        }

        return  listPossibleState;
    }

    public state_unblock_me duplicateState() {
        state_unblock_me a = new state_unblock_me();
        for(bar _bar:listBar) {
            a.addBar(new bar(_bar.getOrientation(),_bar.getX(), _bar.getY(), _bar.getLength(), _bar.isSpecial()));
        }
        a.listLetter = listLetter;
        a.indexChar = indexChar;
        a.parent = parent;

        return a;
    }







}
