package hr.foi.airprojekt.web.model.wrappers;

import hr.foi.airprojekt.web.model.OpisNesrece;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class NesrecaEditView extends NesrecaDetailsView {

    private List<OpisNesrece> sviOpisiNesrece;

}
