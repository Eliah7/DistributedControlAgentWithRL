package ac.udsm.dca.system;

public class Log {


    public static void d(Object msg) {
        System.out.println(msg);
    }

    public static void e(Object msg) {
        System.err.println(msg);
    }
}
