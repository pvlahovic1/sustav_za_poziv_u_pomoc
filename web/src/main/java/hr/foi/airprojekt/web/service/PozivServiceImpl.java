package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.Korisnik;
import hr.foi.airprojekt.web.model.Poziv;
import hr.foi.airprojekt.web.model.wrappers.NesreceTableView;
import hr.foi.airprojekt.web.repository.PozivReposirtory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PozivServiceImpl implements PozivService {

    private final PozivReposirtory pozivReposirtory;

    @Override
    public List<NesreceTableView> fetchAllNesrece() {
        List<NesreceTableView> nesrece = new ArrayList<>();

        List<Poziv> pozivi = pozivReposirtory.findAllByVrijemeRjesavanjaIsNull();

        pozivi.forEach(p -> {
            NesreceTableView ntv = new NesreceTableView();
            ntv.setIdPoziva(p.getIdPoziv());
            ntv.setVrijemePrimitka(p.getVrijemePrimitka());

            Korisnik k = p.getKorisnik();

            ntv.setIme(k.getIme());
            ntv.setPrezime(k.getPrezime());
            ntv.setOib(k.getOib());
            ntv.setBrojMobitela(k.getBrojMob());

            nesrece.add(ntv);
        });

        return nesrece;
    }

}
