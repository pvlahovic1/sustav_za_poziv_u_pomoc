package hr.foi.airprojekt.model.wrapper;

import lombok.Data;

import java.io.Serializable;

@Data
public class PozivWrapper implements Serializable {

    private String oib;
    private double x;
    private double y;

}