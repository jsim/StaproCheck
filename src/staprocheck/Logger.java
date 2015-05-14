package staprocheck;

/**
 *
 * @author jsim
 */
public class Logger {

    void info(Exception ex) {
        System.out.println("ERROR : " + ex.getMessage());
    }

    void info(String msg, Object... params) {
        System.out.println("INFO  : " + String.format(msg, params));
    }

}
