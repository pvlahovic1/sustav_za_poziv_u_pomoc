package hr.foi.airprojekt;

import hr.foi.airprojekt.model.wrapper.OranizacijaTipWrapper;
import hr.foi.airprojekt.model.wrapper.OrganizacijaWrapper;
import hr.foi.airprojekt.service.OrganizacijaService;
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

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizacijaRestControllerShould {

    @MockBean
    private OrganizacijaService organizacijaService;

    private OrganizacijaWrapper organizacija;
    private OranizacijaTipWrapper organizacijaTip;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setup() {
        generateEntitys();
        mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    private void generateEntitys() {
        organizacijaTip = new OranizacijaTipWrapper();
        organizacijaTip.setNaziv("Tip test");
        organizacijaTip.setSlikaURL("url_slike");

        organizacija = new OrganizacijaWrapper();
        organizacija.setNaziv("Organizacija test");
        organizacija.setOpis("Ovo je opis testne organizacije");
        organizacija.setBrojHitnih(10);
        organizacija.setBrojNehitnih(10);
        organizacija.setXKoordinata(0.0);
        organizacija.setYKoordinata(0.0);
        organizacija.setTipOrganizacijeList(Collections.singletonList(organizacijaTip));

    }

    @Test
    public void returAllOrganizacija_onProvideAllOrganizacija() throws Exception {
        FieldDescriptor[] book = new FieldDescriptor[]{
                fieldWithPath("[].naziv").description("Naziv organizacije"),
                fieldWithPath("[].opis").description("Kratki opis organizacije"),
                fieldWithPath("[].brojHitnih").description("Trenutni broj hitnih slučajeva u toj organizaciji"),
                fieldWithPath("[].brojNehitnih").description("Trenutni broj nehitnih slučajeva u toj organizacij"),
                fieldWithPath("[].tipOrganizacijeList").description("Lista tipova kojima pripada ova organizacija"),
                fieldWithPath("[].tipOrganizacijeList.[].naziv").description("Naziv tipa organizacije"),
                fieldWithPath("[].tipOrganizacijeList.[].slikaURL").description("URL slike tipa organizacije"),
                fieldWithPath("[].ykoordinata").description("Y koordinata organizacije"),
                fieldWithPath("[].xkoordinata").description("X koordinata organizacije")
        };

        when(organizacijaService.fetchAllOrganizacija()).thenReturn(Collections.singletonList(organizacija));

        mvc.perform(get("/rest-api/ogranizacija"))
                .andExpect(status().isOk())
                .andDo(document("oranizacija-all", responseFields(book)));

    }

    @Test
    public void returSearchedOrganizacija_onProvideOrganizacijaCloseToMe() throws Exception {
        FieldDescriptor[] requestFields = new FieldDescriptor[]{
                fieldWithPath("xkoordinata").description("X koordinata korisnika"),
                fieldWithPath("ykoordinata").description("Y koordinata korisnika"),
                fieldWithPath("udaljenost").description("Radius udaljenosti korisnika i bolnice")
        };

        String content = "{\"xkoordinata\": 45.810209,\"ykoordinata\": 15.970738,\"udaljenost\": 2}";

        when(organizacijaService.fetchaAllOrganizacijaBy(any())).thenReturn(Collections.singletonList(organizacija));

        mvc.perform(post("/rest-api/ogranizacija")
                .contentType(APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document("oranizacija-selected", requestFields(requestFields)));
    }

}
