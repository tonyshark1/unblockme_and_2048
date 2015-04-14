package com.minhtri;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by le on 3/29/2015.
 */
public class HillClimbing extends algorithm {


    /*
    calculate score of a state
    weight matrix
    -------
    7 6 5 4
    6 5 4 3
    5 4 3 2
    4 3 2 1
    -------
     */
    public static double score(int[][] current) {
        int score1 = current[0][0] * 7 + current[0][1] * 6 + current[0][2] * 5 + current[0][3] * 4
                + current[1][0] * 6 + current[1][1] * 5 + current[1][2] * 4 + current[1][3] * 3
                + current[2][0] * 5 + current[2][1] * 4 + current[2][2] * 3 + current[2][3] * 2
                + current[3][0] * 4 + current[3][1] * 3 + current[3][2] * 2 + current[3][3] * 1;
        int score2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (current[i][j] == 0) score2 = score2 + 1;
            }
        }
        return score1 - score2;
    }

    public static void solve_2048(state_2048 start) {
        if (start.isWin()) {
            System.out.println("solution founded");
            showsolution_2048(start);
        } else {
            double maxscore = score(start.getmatrix());
            boolean terminate = true;
            state_2048 next = start;
            ArrayList<state_2048> ListNextState = start.getListNextStateDFS();
            for (state_2048 in : ListNextState) {
                double score = score(in.getmatrix());
                if (score > maxscore) {
                    next = in;
                    maxscore = score;
                    terminate = false;
                    break;
                }
            }
            if (terminate) {
                showsolution_2048(start);
                System.out.println("local maximum");
            } else {
                solve_2048(next);
            }
        }
    }


    public static void solve_unblock_me(state_unblock_me input) {
        open_unblock_me.add(0, input);
        boolean solutionFounded = false;
        while (open_unblock_me.size() != 0) {
            state_unblock_me start = open_unblock_me.remove(0);
            close_unblock_me.add(start);
            System.out.println();
            in_unblock_me(start);
            if (start.iswin()) {
                showsolution_Unblock_Me(start);
                System.out.println("Solution found");
                solutionFounded = true;
                break;
            } else {

                ArrayList<state_unblock_me> allnextstate = start.allNextState();

                for (state_unblock_me s : allnextstate) {
                    boolean exist = false;
                    for (state_unblock_me s1 : close_unblock_me) {
                        if (s.equal(s1)) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        open_unblock_me.add(s);
                    }
                }
                boolean notbigger = true;
                while (open_unblock_me.size() != 0) {
                    int size = open_unblock_me.size();
                    Random myrandom = new Random();
                    int index = myrandom.nextInt(size);
                    state_unblock_me first = open_unblock_me.remove(index);
                    int currentscore = start.score();
                    int nextscore = first.score();
                    if (nextscore <= currentscore) {
                        open_unblock_me.clear();
                        open_unblock_me.add(first);
                        notbigger = false;
                        break;
                    }
                }
                if (notbigger) break;
            }
        }
        if (!solutionFounded) {
            System.out.println("local maximum");
        }

    }

}
