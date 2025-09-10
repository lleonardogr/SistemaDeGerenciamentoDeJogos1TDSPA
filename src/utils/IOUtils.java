package utils;

import java.util.Scanner;

public class IOUtils {
    public static int scanInt(Scanner scanner){
        var value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
