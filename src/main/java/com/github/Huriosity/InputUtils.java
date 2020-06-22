package com.github.Huriosity;

import java.util.Scanner;

public class InputUtils {
    public static int getInt(){
        Integer id = null;
        Scanner scanner = new Scanner(System.in);
        while (id == null) {
            System.out.println("Input int value");
            String tmp = scanner.nextLine();
            if(isDigit(tmp)){
                id = Integer.parseInt(tmp);
            }
        }
        return id;
    }

    public static Integer getInteger(){
        Integer res = null;
        Scanner scanner = new Scanner(System.in);
        while (res == null) {
            String tmp = scanner.nextLine();
            if(isDigit(tmp)){
                return Integer.parseInt(tmp);
            } else if (tmp.equals("") || tmp == null) {
                return null;
            } else {
                System.err.println("It's not getInteger!!!");
            }
        }
        return null;
    }

    public static boolean isDigit(String strNum){
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
