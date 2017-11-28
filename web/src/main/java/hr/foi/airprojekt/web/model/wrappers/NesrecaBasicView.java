package hr.foi.airprojekt.web.model.wrappers;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NesrecaBasicView {

    private Integer idPoziva;
    private String oib;
    private String ime;
    private String prezime;
    private String brojMobitela;
    private LocalDateTime vrijemePrimitka;

}
