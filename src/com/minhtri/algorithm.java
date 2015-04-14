package com.minhtri;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by le on 4/12/2015.
 */
public class algorithm {

    public static ArrayList<state_unblock_me> open_unblock_me = new ArrayList<state_unblock_me>();
    public static ArrayList<state_unblock_me> close_unblock_me = new ArrayList<state_unblock_me>();

    public static ArrayList<state_2048> open_2048 = new ArrayList<state_2048>();
    public static ArrayList<state_2048> close_2048 = new ArrayList<state_2048>();


    public static void in_unblock_me(state_unblock_me input) {
        for (int i = 0; i < 7; i++) {
            System.out.print("--");
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print("|");
            for (int j = 0; j < 6; j++) {
                System.out.print(input.board[i][j] + " ");
            }
            if (i != 2) {
                System.out.print("|");
            }

            System.out.println();
        }
        for (int i = 0; i < 7; i++) {
            System.out.print("--");
        }
        System.out.println();
    }

    public static void in_2048(state_2048 input) {
        for (int i = 0; i < 25; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.print("|");
            System.out.printf("%-5s %-5s %-5s %-5s", input.matrix[i][0], input.matrix[i][1], input.matrix[i][2], input.matrix[i][3]);
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < 25; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void showsolution_2048(state_2048 input) {
        Stack<state_2048> stack = new Stack<state_2048>();
        stack.push(input);
        while (input.parent != null) {
            stack.push(input.parent);
            input = input.parent;
        }
        int step = 0;
        while (!stack.empty()) {
            in_2048(stack.pop());
            step++;
        }
        System.out.println("number step = " + step);
    }

    public static void showsolution_Unblock_Me(state_unblock_me input) {
        Stack<state_unblock_me> stack = new Stack<state_unblock_me>();
        stack.push(input);
        while (input.parent != null) {
            stack.push(input.parent);
            input = input.parent;
        }
        int step = 0;
        while (!stack.empty()) {
            in_unblock_me(stack.pop());
            step++;
        }
        System.out.println("number step = " + step);
    }

}
