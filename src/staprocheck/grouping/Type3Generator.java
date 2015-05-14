package staprocheck.grouping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import staprocheck.PersonDiet;

/**
 *
 * @author jsim
 */
public class Type3Generator implements KeyGenerator {

    @Override
    public String getDietKey(PersonDiet pd) {
        final DateFormat sdf = new SimpleDateFormat("d-M-yyyy");

        final StringBuilder sb = new StringBuilder();

        sb.append(sdf.format(pd.getTargetDate()));
        sb.append('.');
        sb.append(pd.getWard());
        sb.append(".d");

        return sb.toString();
    }

    @Override
    public String getAdditionKey(PersonDiet pd, String ad) {
        if (ad == null || ad.isEmpty()) {
            return null;
        }

        final DateFormat sdf = new SimpleDateFormat("d-M-yyyy");

        final StringBuilder sb = new StringBuilder();

        sb.append(sdf.format(pd.getTargetDate()));
        sb.append('.');
        sb.append(pd.getWard());
        sb.append(".a");

        return sb.toString();
    }

    @Override
    public String getHeader() {
        final StringBuilder sb = new StringBuilder();

        sb.append("0-Date");
        sb.append('.');
        sb.append("Ward");

        return sb.toString();
    }

}
