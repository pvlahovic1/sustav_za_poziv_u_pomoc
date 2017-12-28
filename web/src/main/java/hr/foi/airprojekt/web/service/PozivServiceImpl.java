package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.Dispatcher;
import hr.foi.airprojekt.web.model.Korisnik;
import hr.foi.airprojekt.web.model.Poziv;
import hr.foi.airprojekt.web.model.wrappers.NesrecaBasicView;
import hr.foi.airprojekt.web.model.wrappers.NesrecaDetailsView;
import hr.foi.airprojekt.web.model.wrappers.NesrecaEditDto;
import hr.foi.airprojekt.web.model.wrappers.NesrecaEditView;
import hr.foi.airprojekt.web.repository.OpisNesreceRepository;
import hr.foi.airprojekt.web.repository.PozivReposirtory;
import hr.foi.airprojekt.web.sender.PushNotificationSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PozivServiceImpl implements PozivService {

    private final PozivReposirtory pozivReposirtory;
    private final OpisNesreceRepository opisNesreceRepository;
    private final PushNotificationSenderService pushNotificationSenderService;

    @Override
    public List<NesrecaBasicView> fetchAllNesrece() {
        List<NesrecaBasicView> nesrece = new ArrayList<>();

        List<Poziv> pozivi = pozivReposirtory.findAllByVrijemeRjesavanjaIsNullOrderByVrijemeRjesavanjaDesc();

        pozivi.forEach(p -> {
            NesrecaBasicView ntv = new NesrecaBasicView();
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

    @Override
    public NesrecaDetailsView fetchNesrecaDetailViewByNesrecaId(int id) {
        Poziv p = pozivReposirtory.findOne(id);

        NesrecaDetailsView ndv = new NesrecaDetailsView();

        ndv.setIdPoziva(p.getIdPoziv());
        ndv.setX(p.getXKoordinata());
        ndv.setY(p.getYKoordinata());
        ndv.setVrijemePrimitka(p.getVrijemePrimitka());
        ndv.setOpisiNesrece(new ArrayList<>(p.getOpisiNesrece()));

        Korisnik korisnik = p.getKorisnik();

        ndv.setIme(korisnik.getIme());
        ndv.setPrezime(korisnik.getPrezime());
        ndv.setBrojMobitela(korisnik.getBrojMob());
        ndv.setOib(korisnik.getOib());

        return ndv;
    }

    @Override
    public NesrecaDetailsView updateNesreca(NesrecaEditDto nesrecaEditDto) {
        Poziv poziv = pozivReposirtory.findOne(nesrecaEditDto.getIdNesrece());

        poziv.setOpisiNesrece(opisNesreceRepository.findAllByIdIn(nesrecaEditDto.getOpisiNesrece()));

        pozivReposirtory.save(poziv);

        return fetchNesrecaDetailViewByNesrecaId(nesrecaEditDto.getIdNesrece());
    }

    @Override
    public NesrecaEditView fetchEditWrapper(int id) {
        Poziv p = pozivReposirtory.findOne(id);
        NesrecaEditView editWrapper = new NesrecaEditView();

        editWrapper.setIdPoziva(p.getIdPoziv());
        editWrapper.setX(p.getXKoordinata());
        editWrapper.setY(p.getYKoordinata());
        editWrapper.setOpisiNesrece(new ArrayList<>(p.getOpisiNesrece()));
        editWrapper.setSviOpisiNesrece(opisNesreceRepository.findAll());
        editWrapper.setVrijemePrimitka(p.getVrijemePrimitka());

        Korisnik k = p.getKorisnik();

        editWrapper.setIme(k.getIme());
        editWrapper.setPrezime(k.getPrezime());
        editWrapper.setBrojMobitela(k.getBrojMob());
        editWrapper.setOib(k.getOib());


        return editWrapper;
    }

    @Override
    public void makeNesrecaResolved(int id) {
        Poziv p = pozivReposirtory.findOne(id);

        p.setVrijemeRjesavanja(LocalDateTime.now());

        pozivReposirtory.save(p);
    }

    @Override
    public String sendPushNotification(int idNesrece) {
         Poziv poziv = pozivReposirtory.findOne(idNesrece);
         Korisnik korisnik = poziv.getKorisnik();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Dispatcher dispatcher = (Dispatcher)authentication.getPrincipal();

        String title = "ProCare poziv u pomoc";
        String message = "Vas poziv preuzeo je: " + dispatcher.getIme() + " " + dispatcher.getPrezime();

        String povratnaInformacija = "Notifikacija je uspješno poslana!";

        if (StringUtils.isEmpty(korisnik.getMessageToken())) {
            povratnaInformacija = "Korisnik nema token za slanje notifikacije";
        } else {
            try {
                pushNotificationSenderService.sendPushNotification(korisnik.getMessageToken(), title, message);
            } catch (Exception e) {
                log.info(e.getMessage());
                povratnaInformacija = "Došlo je do greške prilikom slanja notifikacije";
            }
        }

        return povratnaInformacija;
    }

}
