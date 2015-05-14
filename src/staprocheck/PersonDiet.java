package staprocheck;

import java.util.Date;

/**
 *
 * @author jsim
 */
public class PersonDiet {

    private long id;
    private Date created;
    private Date targetDate;
    private Date done;
    private String source;
    private String mealType;
    private String patient;
    private String ident;
    private String diet;
    private String note1;
    private String note2;
    private String ad1 = "";
    private String ad2 = "";
    private String ad3 = "";
    private String ad4 = "";
    private String ad5 = "";
    private String ward;
    private String fail;

    public String getAd1() {
        return ad1;
    }

    public void setAd1(String ad1) {
        this.ad1 = ad1;
    }

    public String getAd2() {
        return ad2;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2;
    }

    public String getAd3() {
        return ad3;
    }

    public void setAd3(String ad3) {
        this.ad3 = ad3;
    }

    public String getAd4() {
        return ad4;
    }

    public void setAd4(String ad4) {
        this.ad4 = ad4;
    }

    public String getAd5() {
        return ad5;
    }

    public void setAd5(String ad5) {
        this.ad5 = ad5;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        this.done = done;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
