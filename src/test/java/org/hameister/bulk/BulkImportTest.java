package org.hameister.bulk;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hameister.bulk.data.Item;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hameister on 02.12.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = BulkImporterApplication.class)
@ActiveProfiles("test")
public class BulkImportTest {

    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    @BeforeClass
    public static void setupConnection() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/items";
    }

    @Before
    public void setup() {
        requestSpecBuilder.setContentType(ContentType.JSON);
    }


    @Test
    public void bulkimportShouldImportMultiplePersons() {
        int numberOfItems = 4;
        Response response =
                given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(createItems(numberOfItems))
                        .when()
                        .post("/")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();


        List<String> resultList = from(response.body().asString()).getList(".");
        assertThat(resultList.size(), is(numberOfItems));

    }

    private List<Item> createItems(int numberOfItems) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            Item item = new Item();
            item.setDescription("Item " + i);
            item.setLocation("Table");
            item.setId(UUID.randomUUID().toString());
            items.add(item);
        }
        return items;
    }
}
