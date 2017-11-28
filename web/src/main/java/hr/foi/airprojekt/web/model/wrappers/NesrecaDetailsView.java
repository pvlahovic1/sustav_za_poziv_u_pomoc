package hr.foi.airprojekt.web.model.wrappers;

import hr.foi.airprojekt.web.model.OpisNesrece;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NesrecaDetailsView extends NesrecaBasicView {

    private double x;
    private double y;
    List<OpisNesrece> opisiNesrece;

}
