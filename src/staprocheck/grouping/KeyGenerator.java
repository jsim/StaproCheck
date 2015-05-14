package staprocheck.grouping;

import staprocheck.PersonDiet;

/**
 *
 * @author jsim
 */
public interface KeyGenerator {

    String getHeader();

    String getDietKey(PersonDiet pd);

    String getAdditionKey(PersonDiet pd, String ad);
}
