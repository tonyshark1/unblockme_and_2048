package com.minhtri;

public class Main {

    public static void main(String[] args) {
	// write your code here
        state start = new state();
        start.addBar(new bar(false,0,0,2,false));
        start.addBar(new bar(true,2,0,2,false));
        start.addBar(new bar(true,5,0,2,false));
        start.addBar(new bar(true,0,1,2,false));
        start.addBar(new bar(false,3,1,2,false));
        start.addBar(new bar(false,1,2,2,true));

        start.addBar(new bar(true,3,2,2,false));
        start.addBar(new bar(true,4,2,2,false));
        start.addBar(new bar(true,5,2,2,false));
        start.addBar(new bar(false,0,3,2,false));
        start.addBar(new bar(false,0,4,2,false));
        start.addBar(new bar(false,0,5,2,false));

        start.addBar(new bar(false,2,5,2,false));
        start.addBar(new bar(true,4,4,2,false));

        /*start.addBar(new bar(true,0,0,3,false));
        start.addBar(new bar(true,3,0,2,false));
        start.addBar(new bar(true,5,0,2,false));
        start.addBar(new bar(true,4,2,3,false));
        start.addBar(new bar(true,5,3,2,false));
        start.addBar(new bar(true,2,4,2,false));

        start.addBar(new bar(false,1,1,2,false));
        start.addBar(new bar(false,0,3,3,false));
        start.addBar(new bar(false,0,5,2,false));
        start.addBar(new bar(false,3,5,2,false));

        start.addBar(new bar(false,2,2,2,true));*/

        DFS.in(start);

        DFS.solve(start);

        //BRFS.in(start);

        //BRFS.solve(start);
    }
}
