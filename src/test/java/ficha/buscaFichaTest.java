package ficha;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.*;
import util.Conexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

public class buscaFichaTest {

    private String urlBase = "http://audsat-teste.dextra.tech:8081/v1";
    private String externalID = "348";
    private String invalidExternalID= "Teste";


    @BeforeSuite
    public void inserirFicha() throws IOException, SQLException {

        Conexao conexao = new Conexao();
        Connection cc = conexao.conectarBancoTeste();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO gisweb.insurance VALUES")
                .append("(99999999999,NOW(),NOW(),'user@audsat.com.br',1,'c4b65a1f-cb92-4311-b540-8563a99108e2','c4b65a1f-cb92-4311-b540-8563a99108e2',12, 'milho', NOW(),37,null,")
                .append(externalID)
                .append(",102901029122,00000000000,'Joao de teste', '10290192', '1209102','0129901',true, true,'2018-09-30 21:00:00.0','2018-09-30 21:00:00.0',1,1,1,6);");

        Statement st = cc.createStatement();
        st.executeUpdate(sql.toString());
        st.close();
        cc.close();
    }

    @AfterSuite
    public void removerFicha() throws IOException, SQLException {

        Conexao conexao = new Conexao();
        Connection cc = conexao.conectarBancoTeste();
        String sql = "delete from gisweb.insurance where external_id =?";
        PreparedStatement ps = cc.prepareStatement(sql);
        ps.setString(1,externalID);
        ps.executeUpdate();
        cc.close();
    }

    @Test
    public void buscaFichaValidaValida() {

        Response r = get(urlBase + "/fichas/" + externalID);
        MatcherAssert.assertThat(r.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/ficha.json"));

        given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(urlBase + "/fichas/" + externalID)
                .then()
                .statusCode(200)
                .body("numero_proposta", is("10290192"))
                .body("nome_segurado", containsString("Joao"));
    }

    @Test
    public void buscaFichaInexistente(){

        Response r = get(urlBase + "/fichas/" + invalidExternalID);
        System.out.println(r.asString());
        MatcherAssert.assertThat(r.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/fichaNotFound.json"));
        given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(urlBase + "/fichas/" + invalidExternalID)
                .then()
                .statusCode(404)
                .body("messageapierror.message", is("Not Found"))
                .body("messageapierror.status", is("NOT_FOUND"));


    }
}
