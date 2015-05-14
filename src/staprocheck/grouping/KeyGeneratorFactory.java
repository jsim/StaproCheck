package staprocheck.grouping;

/**
 *
 * @author jsim
 */
public class KeyGeneratorFactory {

    public static KeyGenerator getGenerator(int type) {
        if (type == 2) {
            return new Type2Generator();
        } else if (type == 3) {
            return new Type3Generator();
        } else if (type == 4) {
            return new Type4Generator();
        } else {
            return new Type1Generator();
        }
    }
}
