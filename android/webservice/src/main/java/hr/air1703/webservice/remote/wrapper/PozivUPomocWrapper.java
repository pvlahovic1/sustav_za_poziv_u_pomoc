package hr.air1703.webservice.remote.wrapper;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public class PozivUPomocWrapper {

    private String oib;
    private String razlog;
    private double x;
    private double y;

    public PozivUPomocWrapper() {
    }

    public PozivUPomocWrapper(String oib, String razlog, double x, double y) {
        this.oib = oib;
        this.razlog = razlog;
        this.x = x;
        this.y = y;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
