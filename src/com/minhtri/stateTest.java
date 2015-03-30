package com.minhtri;

import org.junit.Test;

import static org.junit.Assert.*;

public class stateTest {

    state start;

    @Test
    public void testCanMove() throws Exception {
        start = new state();
        start.addBar(new bar(false,0,0,2,false));
        start.addBar(new bar(true,2,0,2,false));
        start.addBar(new bar(true,5,0,2,false));
        start.addBar(new bar(true,0,1,2,false));
        start.addBar(new bar(false,3,1,2,false));
        start.addBar(new bar(false,1,2,2,true));

        start.addBar(new bar(true,3,3,2,false));

        start.addBar(new bar(true,5,3,2,false));
        start.addBar(new bar(false,0,3,2,false));
        start.addBar(new bar(false,0,4,2,false));
        start.addBar(new bar(false,0,5,2,false));

        start.addBar(new bar(false,2,5,2,false));
        start.addBar(new bar(true,4,4,2,false));

        DFS.in(start);
        boolean a = start.isRedFree();

        if(a) System.out.println("red free");







    }
}