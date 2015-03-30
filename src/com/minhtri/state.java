package com.minhtri;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by le on 3/26/2015.
 */
public class state {

    ArrayList<bar> listBar;

    char[] listLetter;

    int indexChar;

    int width = 6;

    int height = 6;

    state parent;

    char[][] board = new char[width][height];

    bar redbar = null;

    //initial state with a board contain all space character
    public state() {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = ' ';
            }
        }

        indexChar = 0;

        listLetter = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u'};

        listBar = new ArrayList<bar>();
    }

    //check if two state is identity
    public boolean equal(state input) {
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
    public boolean isRedFree() {
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
                if(y + length >= height - 1) return false;
                if(board[y+length][x] == ' ') return true; else return false;
            }
        }else{
            if(direction == 3){
                if(x == 0) return  false;
                if(board[y][x-1] == ' ') return true; else return false;
            }else if(direction == 4){
                if(x + length >= width - 1 ) return false;
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

    public ArrayList<state> allNextState(){

        ArrayList<state> listPossibleState = new ArrayList<state>();

        for(bar _bar:listBar){
            if(canMove(_bar,1)){
                state a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,1);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,2)){
                state a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,2);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,3)){
                state a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,3);
                a.parent = this;
                listPossibleState.add(a);
            }
            if(canMove(_bar,4)){
                state a = this.duplicateState();
                int x = _bar.getX();
                int y = _bar.getY();
                a.move(x,y,4);
                a.parent = this;
                listPossibleState.add(a);
            }
        }

        return  listPossibleState;
    }

    public state duplicateState(){
        state a = new state();
        for(bar _bar:listBar) {
            a.addBar(new bar(_bar.getOrientation(),_bar.getX(), _bar.getY(), _bar.getLength(), _bar.isSpecial()));
        }
        a.listLetter = listLetter;
        a.indexChar = indexChar;
        a.parent = parent;

        return a;
    }







}
