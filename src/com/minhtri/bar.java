package com.minhtri;

/**
 * Created by le on 3/26/2015.
 */
public class bar {
    private boolean isVertical;
    private int x;
    private int y;
    private int length;
    private boolean isSpecial;
    private state current;
    private char letter;

    public bar(boolean _vertical,int _x,int _y,int _length, boolean _isSpecial){
        isVertical = _vertical;
        x = _x;
        y = _y;
        length = _length;
        isSpecial = _isSpecial;
    }

    public void setLetter(char input){
        letter = input;
    }

    public void setPos(int _x, int _y){
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public boolean isSpecial(){
        return  isSpecial;
    }

    public char getLetter(){
        return letter;
    }

    public boolean getOrientation(){
        return isVertical;
    }

}
