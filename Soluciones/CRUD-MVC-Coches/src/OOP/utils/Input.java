package OOP.utils;

import java.util.Scanner;

public class Input {
    public static String getString(String massge){
        System.out.println(massge);
        return new Scanner(System.in).nextLine().trim();
    }
}
