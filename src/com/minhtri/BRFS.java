package com.minhtri;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by le on 3/29/2015.
 */
public class BRFS {

    public static ArrayList<state> queue = new ArrayList<state>();
    public static ArrayList<state> close = new ArrayList<state>();

    public  static void in(state input){
        for(int i = 0; i< 7;i++){
            System.out.print("--");
        }
        System.out.println();
        for(int i = 0;i<6;i++){
            System.out.print("|");
            for(int j = 0;j<6;j++){
                System.out.print(input.board[i][j] + " ");
            }
            if(i!=2) {
                System.out.print("|");
            }
            System.out.println();
        }
        for(int i = 0; i< 7;i++){
            System.out.print("--");
        }
        System.out.println();
    }

    public static void showsolution(state input){
        Stack<state> stack = new Stack<state>();
        stack.push(input);
        while(input.parent!=null){
            stack.push(input.parent);
            input = input.parent;
        }
        while(!stack.empty()){
            in(stack.pop());
        }
    }

    public static void solve(state start){
        queue.add(start);
        System.out.println("..Solving...");
        while(!queue.isEmpty()){
            state a = queue.remove(0);
            //in(a);
            if(a.isRedFree()){
                System.out.println("solution founded");
                showsolution(a);
                break;
            }else {
                ArrayList<state> nextState = a.allNextState();
                boolean isclose = false;
                for(state old:close){
                    if(old.equal(a)){
                        isclose = true;
                        break;
                    }
                }
                if(!isclose) {
                    close.add(a);
                }
                for (state next : nextState) {
                    //System.out.println("new state = ");
                   // in(next);
                    boolean isvalid = true;
                    for(state old:close){
                        //System.out.println("old state = ");
                        //in(old);
                        if(old.equal(next)){  isvalid = false; break;}
                        for(state open:queue){
                           // System.out.println("open state = ");
                           // in(open);
                            if(open.equal(next)){ isvalid = false; break;}
                        }
                    }

                    if(isvalid) queue.add(next);
                }
            }
            //System.out.println("solving");
        }
    }

}
