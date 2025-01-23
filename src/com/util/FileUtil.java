package com.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileUtil {
    private static Scanner scanner = null;

    public static Scanner inputFile() {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String className = stackTraceElements[stackTraceElements.length - 1].getClassName();
            return inputFile(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scanner inputFile(Class<?> className) {
        String name = className.getSimpleName();
        Path path = Paths.get(System.getProperty("user.dir"), "src");
        for (String fragment : className.getPackageName().split("[.]"))
            path = path.resolve(fragment);
        set(
                path.resolve("input").resolve(name).toString(),
                path.resolve("output").resolve(name).toString()
        );
        return scanner;
    }

    public static void set(String input, String output) {
        setInputStream(input);
        setOutputStream(output);
        scanner = new Scanner(System.in);
    }

    private static void setInputStream(String path) {
        try {
            File file = new File(path);
            InputStream in = new FileInputStream(file);
            System.setIn(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setOutputStream(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) file.createNewFile();
            PrintStream out = new PrintStream(new FileOutputStream(file), true);
            System.setOut(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
