package util;

public class Logger{
    public static void log(Object...args){
        System.out.println(string(args));
    }
    public static String string(Object...args){
        StringBuilder builder = new StringBuilder();
        for(Object object:args){
            builder.append(object);
        }
        return builder.toString();
    }
}