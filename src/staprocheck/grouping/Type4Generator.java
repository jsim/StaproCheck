package staprocheck.grouping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import staprocheck.PersonDiet;

public class Type4Generator implements KeyGenerator {

    @Override
    public String getDietKey(PersonDiet pd) {
        final DateFormat sdf = new SimpleDateFormat("d-M-yyyy");

        final StringBuilder sb = new StringBuilder();

        sb.append(sdf.format(pd.getTargetDate()));
        sb.append('\t');
        sb.append(pd.getWard());
        sb.append('\t');
        sb.append(pd.getMealType());
        sb.append('\t');
        sb.append(pd.getDiet());
        sb.append('\t');
        sb.append(pd.getAd1());
        sb.append('\t');
        sb.append(pd.getAd2());
        sb.append('\t');
        sb.append(pd.getAd3());
        sb.append('\t');
        sb.append(pd.getAd4());
        sb.append('\t');
        sb.append(pd.getAd5());
        sb.append('\t');
        sb.append(pd.getPatient());
        sb.append('\t');
        sb.append(pd.getIdent());
        sb.append('\t');
        sb.append(pd.getNote1());
        sb.append('\t');
        sb.append(pd.getNote2());

        return sb.toString();
    }

    @Override
    public String getAdditionKey(PersonDiet pd, String ad) {
        return null;
    }

    @Override
    public String getHeader() {
        final StringBuilder sb = new StringBuilder();

        sb.append("0-Date");
        sb.append('\t');
        sb.append("Ward");
        sb.append('\t');
        sb.append("MT");
        sb.append('\t');
        sb.append("Diet");
        sb.append('\t');
        sb.append("Ad1");
        sb.append('\t');
        sb.append("Ad2");
        sb.append('\t');
        sb.append("Ad3");
        sb.append('\t');
        sb.append("Ad4");
        sb.append('\t');
        sb.append("Ad5");
        sb.append('\t');
        sb.append("Pacient");
        sb.append('\t');
        sb.append("Ident");
        sb.append('\t');
        sb.append("Note1");
        sb.append('\t');
        sb.append("Note2");
        sb.append('\t');

        return sb.toString();
    }
}
