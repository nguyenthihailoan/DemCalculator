package com.lx.ltuddd.democaculator.utils;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by DaiPhongPC on 7/11/2018.
 */

public class InfixPostfix {
    public static Double stringToLong(String input) {
        try {
            Double result = Double.parseDouble(input);
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0d;
        }
    }

    public static boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int checkPriority(String operand) {
        switch (operand) {
            case Contants.OPERATION_ADD:
            case Contants.OPERATION_SUB:
                return 1;
            case Contants.OPERATION_MUL:
            case Contants.OPERATION_DIV:
                return 2;
            case Contants.OPERATION_EXP:
                return 3;
        }
        return -1;
    }

    public static ArrayList<String> infixToPostfix(ArrayList<String> arrValue) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> arrConvert = new ArrayList<>();
        for (String s : arrValue) {
            if (isNumber(s)) {
                arrConvert.add(s);
            } else if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!s.isEmpty() && !stack.peek().equals("(")) {
                    arrConvert.add(stack.pop());
                }
                if (!s.isEmpty() && !stack.peek().equals("(")) return arrConvert;
                else stack.pop();
            } else {
                while (!stack.isEmpty() && checkPriority(s) <= checkPriority(stack.peek())) {
                    arrConvert.add(stack.pop());

                }
                stack.push(s);
            }
        }
        while (!stack.isEmpty()) {
            arrConvert.add(stack.pop());
        }
        return arrConvert;
    }

    public static double caculatorPostfix(ArrayList<String> arrInput) {
        Stack<Double> stack = new Stack<>();
        for (String s : arrInput) {
            if (isNumber(s)) {
                stack.push(stringToLong(s));
            } else {
                double val1 = stack.pop();
                double val2 = stack.pop();
                switch (s) {
                    case Contants.OPERATION_ADD:
                        stack.push(val2 + val1);
                        break;
                    case Contants.OPERATION_SUB:
                        stack.push(val2 - val1);
                        break;
                    case Contants.OPERATION_DIV:
                        stack.push(val2 / val1);
                        break;
                    case Contants.OPERATION_MUL:
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
