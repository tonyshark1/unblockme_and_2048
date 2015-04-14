package com.minhtri;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here


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

        int game = 2;// game == 1 ->solve 2048  game == 2-> solve unblock_me
        int type = 3;
        double starttime;
        double endtime;
        double memory;
        if (game == 1) {

            state_2048 start = new state_2048(64);
            start.resumeGame();
            switch (type) {
                case 1:
                    //DFS.in_unblock_me(start);
                    starttime = System.currentTimeMillis();
                    DFS.solve_2048(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;
                case 2:
                    //BRFS.in_unblock_me(start);
                    starttime = System.currentTimeMillis();
                    BRFS.solve_2048(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;
                case 3:
                    starttime = System.currentTimeMillis();
                    HillClimbing.solve_2048(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;
            }
        } else {
            state_unblock_me start = new state_unblock_me();
            start.resumeGame();
            switch (type) {
                case 1:
                    DFS.in_unblock_me(start);
                    starttime = System.currentTimeMillis();
                    DFS.solve_Unblock_Me(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;
                case 2:
                    BRFS.in_unblock_me(start);
                    starttime = System.currentTimeMillis();
                    BRFS.solve_unblock_me(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;
                case 3:
                    starttime = System.currentTimeMillis();
                    HillClimbing.solve_unblock_me(start);
                    endtime = System.currentTimeMillis();
                    System.out.println("It takes " + (endtime - starttime) + "ms");
                    memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("memory using : " + memory/*/1000000 + "MB"*/);
                    break;

            }

        }




    }
}
