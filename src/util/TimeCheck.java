package util;

public class TimeCheck {
    public static long timeTook(Runnable runnable){
        long start = System.currentTimeMillis();
        try{
            runnable.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return (end-start);
    }
}
