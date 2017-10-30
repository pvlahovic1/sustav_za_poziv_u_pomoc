package hr.foi.airprojekt;

import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.service.KorisnikService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KorisnikRestControllerShould {

    private Korisnik korisnik;

    @MockBean
    private KorisnikService korisnikService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();

        korisnik = new Korisnik();
        korisnik.setIdKorisnik(1);
        korisnik.setOib("12345678903");
        korisnik.setIme("Ime");
        korisnik.setPrezime("Prezime");
        korisnik.setAdresa("Adresa");
        korisnik.setMail("korisnik@mail.com");
        korisnik.setLozinka("lozinka");
        korisnik.setBrojMob("0987654321");
    }

    @Test
    public void returnJSONKorisnik_onProvideKorisnik() throws Exception {
        FieldDescriptor[] book = new FieldDescriptor[] {
                fieldWithPath("idKorisnik").description("Id traženog korisnika"),
                fieldWithPath("oib").description("OIB traženog korisnika"),
                fieldWithPath("ime").description("Ime traženog korisnika"),
                fieldWithPath("prezime").description("Prezime traženog korisnika"),
                fieldWithPath("adresa").description("Adresa traženog korisnika"),
                fieldWithPath("mail").description("eMail adresa traženog korisnika"),
                fieldWithPath("lozinka").description("Lozinka traženog korisnika"),
                fieldWithPath("brojMob").description("Broj mobitela traženog korisnika") };

        when(korisnikService.fetchKorisnikByMailAndLozinka("korisnik@mail.com", "lozinka")).thenReturn(korisnik);

        mvc.perform(post("/rest-api/korisnik/login")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content("{\"mail\":\"korisnik@mail.com\",\"lozinka\":\"lozinka\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andDo(document("korisnik-prijava", responseFields(book)));
    }

    @Test
    public void registerNewKorisnik_onRegisterNewKorisnik() throws Exception {
        String content = "{\"oib\": \"12345678903\", \"ime\": \"Ime\", \"prezime\": \"Prezime\", \"adresa\":" +
                " \"Adresa\", \"mail\": \"mail@mail.com\", \"lozinka\": \"lozinka\", \"brojMob\": \"0987654321\"}";

        mvc.perform(post("/rest-api/korisnik")
                .contentType(APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document("korisnik-registracija"));
    }

    @Test
    public void updateKorisnik_onUpdateKorisnik() throws Exception {
        FieldDescriptor[] book = new FieldDescriptor[] {
                fieldWithPath("idKorisnik").description("Id korisnika"),
                fieldWithPath("oib").description("OIB korisnika"),
                fieldWithPath("ime").description("Izmjenjeno ime korisnika"),
                fieldWithPath("prezime").description("Izmjenjeno prezime korisnika"),
                fieldWithPath("adresa").description("Izmjenjena adresa korisnika"),
                fieldWithPath("mail").description("Izmjenjena eMail adresa korisnika"),
                fieldWithPath("lozinka").description("Izmjenjena lozinka korisnika"),
                fieldWithPath("brojMob").description("Izmjenjen broj mobitela korisnika") };


        when(korisnikService.fetchKorisnikByOib("12345678903")).thenReturn(korisnik);
        when(korisnikService.updateKorisnik(any(), any())).thenReturn(korisnik);

        String content = "{\"oib\": \"12345678903\", \"ime\": \"Ime\", \"prezime\": \"Prezime\", \"adresa\":" +
                " \"Adresa\", \"mail\": \"mail@mail.com\", \"lozinka\": \"lozinka\", \"brojMob\": \"0987654321\"}";

        mvc.perform(post("/rest-api/korisnik/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document("korisnik-update", responseFields(book)));
    }

}
