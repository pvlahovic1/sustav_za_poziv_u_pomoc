package hr.foi.airprojekt.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PozivWrapper implements Serializable {

    private String oib;
    private String razlog;
    private double x;
    private double y;

}
