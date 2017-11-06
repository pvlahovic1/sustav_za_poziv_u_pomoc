package hr.foi.airprojekt;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PozivRestControllerShould {

    @MockBean
    private PozivService pozivService;

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

}
