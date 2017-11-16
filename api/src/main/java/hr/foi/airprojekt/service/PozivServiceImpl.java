package hr.foi.airprojekt.service;

import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.exception.RazlogPozivaException;
import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.model.OpisNesrece;
import hr.foi.airprojekt.model.Poziv;
import hr.foi.airprojekt.model.wrapper.OpisNesreceWrapper;
import hr.foi.airprojekt.model.wrapper.PozivWrapper;
import hr.foi.airprojekt.repository.OpisNesreceRepository;
import hr.foi.airprojekt.repository.PozivRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PozivServiceImpl implements PozivService {

    private final PozivRepository pozivRepository;
    private final KorisnikService korisnikService;
    private final OpisNesreceRepository opisNesreceRepository;

    @Override
    public void addNewPozivUPomoc(PozivWrapper pozivWrapper) {

        Korisnik korisnik = korisnikService.fetchKorisnikByOib(pozivWrapper.getOib());
        OpisNesrece opisNesrece = opisNesreceRepository.findByNaziv(pozivWrapper.getRazlog());

        if (opisNesrece == null) {
            throw new RazlogPozivaException("OpisNesrece doesnt exist!");
        }

        if (korisnik != null) {
            Poziv poziv = new Poziv();
            poziv.setXKoodinata(pozivWrapper.getX());
            poziv.setYKoordinata(pozivWrapper.getY());
            poziv.setVrijemePrimitka(LocalDateTime.now());
            poziv.setKorisnik(korisnik);
            poziv.getOpisiNesrece().add(opisNesrece);

            pozivRepository.save(poziv);
        } else {
           throw new KorisnikCredentialsException("User is missing");
        }
    }

    @Override
    public List<OpisNesreceWrapper> fetchAllReasons() {
        List<OpisNesreceWrapper> opisi = new ArrayList<>();

        opisNesreceRepository.findAll()
                .forEach(opis -> opisi.add(new OpisNesreceWrapper(opis.getId(), opis.getNaziv())));

        return opisi;
    }

}
