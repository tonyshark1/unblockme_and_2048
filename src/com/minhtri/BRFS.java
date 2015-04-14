package com.minhtri;

import java.util.ArrayList;

/**
 * Created by le on 3/29/2015.
 */
public class BRFS extends algorithm {

    public static void solve_unblock_me(state_unblock_me start) {
        open_unblock_me.add(start);
        System.out.println("..Solving...");
        while (!open_unblock_me.isEmpty()) {
            state_unblock_me a = open_unblock_me.remove(0);
            if (a.iswin()) {
                System.out.println("solution founded");
                showsolution_Unblock_Me(a);
                break;
            } else {
                ArrayList<state_unblock_me> nextState = a.allNextState();
                boolean isclose = false;
                for (state_unblock_me old : close_unblock_me) {
                    if (old.equal(a)) {
                        isclose = true;
                        break;
                    }
                }
                if (!isclose) {
                    close_unblock_me.add(a);
                }
                for (state_unblock_me next : nextState) {
                    boolean isvalid = true;
                    for (state_unblock_me old : close_unblock_me) {
                        if (old.equal(next)) {
                            isvalid = false;
                            break;
                        }
                    }
                    for (state_unblock_me open : open_unblock_me) {
                        if (open.equal(next)) {
                            isvalid = false;
                            break;
                        }
                    }

                    if (isvalid) open_unblock_me.add(next);
                }
            }
        }
    }

    public static void solve_2048(state_2048 start) {
        open_2048.add(start);
        System.out.println("..Solving...");
        while (!open_2048.isEmpty()) {
            state_2048 a = open_2048.remove(0);
            if (a.isWin()) {
                System.out.println("solution founded");
                showsolution_2048(a);
                break;
            }else {
                ArrayList<state_2048> nextState = a.getListNextStateBRFS();
                boolean isclose = false;
                for (state_2048 old : close_2048) {
                    if(old.equal(a)){
                        isclose = true;
                        break;
                    }
                }
                if(!isclose) {
                    close_2048.add(a);
                }
                for (state_2048 next : nextState) {
                    boolean isvalid = true;
                    for (state_2048 old : close_2048) {
                        if(old.equal(next)){  isvalid = false; break;}
                    }
                    for (state_2048 open : open_2048) {
                        if (open.equal(next)) {
                            isvalid = false;
                            break;
                        }
                    }
                    if (isvalid) open_2048.add(next);
                }
            }
        }
    }

}
