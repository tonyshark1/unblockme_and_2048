package com.minhtri;

import java.util.ArrayList;

/**
 * Created by le on 3/29/2015.
 */
public class DFS extends algorithm {

    public static void solve_Unblock_Me(state_unblock_me start) {
        open_unblock_me.add(0, start);
        while (!open_unblock_me.isEmpty()) {
            state_unblock_me a = open_unblock_me.remove(0);
            if (a.iswin()) {
                System.out.println("solution founded");
                showsolution_Unblock_Me(a);
                break;
            }else {
                ArrayList<state_unblock_me> nextState = a.allNextState();
                boolean isclose = false;
                for (state_unblock_me old : close_unblock_me) {
                    if(old.equal(a)){
                        isclose = true;
                        break;
                    }
                }
                if(!isclose) {
                    close_unblock_me.add(a);
                }
                for (state_unblock_me next : nextState) {
                    boolean isvalid = true;
                    for (state_unblock_me old : close_unblock_me) {
                        if(old.equal(next)){ isvalid = false; break;}
                    }
                    for (state_unblock_me exist : open_unblock_me) {
                        if(exist.equal(next)){ isvalid = false; break;}
                    }

                    if (isvalid) open_unblock_me.add(0, next);
                }
            }

        }
        System.out.println("ket thuc");
    }

    public static void solve_2048(state_2048 start) {
        open_2048.add(0, start);
        while (!open_2048.isEmpty()) {
            state_2048 current = open_2048.remove(0);
            if (current.isWin()) {  // check if solution is found
                System.out.println("solution founded");
                showsolution_2048(current);
                break;
            } else {
                ArrayList<state_2048> nextState = current.getListNextStateDFS(); // get all next state for DFS algorithm
                boolean isclose = false;
                for (state_2048 old : close_2048) {
                    if (old.equal(current)) {
                        isclose = true;  // state existed in close
                        break;
                    }
                }
                if (!isclose) {  // if a is not in close , add a to close
                    close_2048.add(current);
                }
                for (state_2048 next : nextState) {
                    boolean isvalid = true;
                    for (state_2048 old : close_2048) {
                        if (old.equal(next)) {
                            isvalid = false;
                            break;
                        }    // check each next state if it existed in close list
                    }
                    for (state_2048 exist : open_2048) {
                        if (exist.equal(next)) {
                            isvalid = false;
                            break;
                        }  // check each next state if it existed in open list
                    }

                    if (isvalid) open_2048.add(0, next);
                }
            }

        }
        System.out.println("ket thuc");
    }
}
