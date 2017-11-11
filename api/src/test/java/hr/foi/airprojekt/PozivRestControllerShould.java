package hr.foi.airprojekt;

import hr.foi.airprojekt.model.wrapper.OpisNesreceWrapper;
import hr.foi.airprojekt.service.PozivService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PozivRestControllerShould {

    private OpisNesreceWrapper opisNesreceWrapper;

    @MockBean
    private PozivService pozivService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setup() {
        generateOpisNesrece();
        mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    private void generateOpisNesrece() {
        opisNesreceWrapper = new OpisNesreceWrapper(1, "Testni naziv");
    }

    @Test
    public void saveHelpCall_onCallHelp() throws Exception {
        FieldDescriptor[] book = new FieldDescriptor[]{
                fieldWithPath("oib").description("OIB korisnika koji zove pomoć"),
                fieldWithPath("x").description("X koordinata korisnika"),
                fieldWithPath("y").description("Y koordinata korisnika")
        };

        mvc.perform(post("/rest-api/poziv")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"oib\":\"12345678903\",\"x\": 0 , \"y\": 0}"))
                .andExpect(status().isOk())
                .andDo(document("poziv", requestFields(book)));
    }

    @Test
    public void provideAllReasons_onProvideAllReasons() throws Exception {
        FieldDescriptor[] responseFields = new FieldDescriptor[]{
                fieldWithPath("[].id").description("Id opisa nesreće"),
                fieldWithPath("[].naziv").description("Naziv opisa nereće")
        };

        when(pozivService.fetchAllReasons()).thenReturn(Collections.singletonList(opisNesreceWrapper));

        mvc.perform(get("/rest-api/poziv/razlozi"))
                .andExpect(status().isOk())
                .andDo(document("poziv-razlozi", responseFields(responseFields)));
    }

}
