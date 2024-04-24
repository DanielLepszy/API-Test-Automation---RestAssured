import Models.Animal;
import TestBase.TestsBase;
import TestUtils.ClearDataScript;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Tag("Smoke")
public class AnimalApiSmokeTest extends TestsBase {

    @BeforeAll
    public static void setUpConf(){
        baseConfigSetup();
    }

    /**
     * Rollback all changes before each test. It makes each test case independent
     * **/
    @BeforeEach
    public void setUp() throws Exception {
        ClearDataScript.rollbackDataToInitialDataStage(gson);
    }

    @Test()
    @DisplayName(("Get All Animals"))
    @Tag("Positive_SC")
    public void getAnimalsTest() {

        //Arrange
        List<Animal> expectedAnimalsObjectList = new ArrayList<>(Arrays.asList(
                new Animal(1,19, "Marinna"),
                new Animal( 2,9, "Erhart"),
                new Animal( 3, 1, "Tom")));

        //Act
        String response = getAllAnimalObjects();
        List<Animal> animalsObjectList = mapResponeToAnimalList(response); // Map JSON to Models.Animal object

        //Assert
        Assertions.assertTrue(expectedAnimalsObjectList.equals(animalsObjectList));
    }

    @Test()
    @DisplayName(("Get Non Existed Object By ID"))
    @Tag("Negative_SC")
    public void getNonExistingObjectByIdTest() {
        Animal animal = new Animal( 11111,1111, "XYZ");

        RestAssured
                .given()
                .when()
                .get(animal.getStringId())
                .then()
                .statusCode(404)
                .body(equalTo("{}"));

    }
    @Test()
    @DisplayName(("Update Non Existed Object"))
    @Tag("Negative_SC")
    public void updateNonExistingObjectTest () {
        Animal animal = new Animal( 11111,1111, "XYZ");
        RestAssured
                .given()
                .body(animal)
                .contentType(ContentType.JSON)
                .when()
                .put(animal.getStringId())
                .then()
                .statusCode(404)
                .body(equalTo("{}"));


    }
    @Test()
    @DisplayName(("Delete Non Existed Object By ID"))
    @Tag("Negative_SC")
    public void deleteNonExistingObjectTest() {
        //CRUD operations
        Animal animal = new Animal( 11111,1111, "XYZ");
        RestAssured
                .given()
                .when()
                .delete(animal.getStringId())
                .then()
                .statusCode(404);
    }
    @Test()
    @DisplayName(("Post Animal Object With Wrong Attributes"))
    @Tag("Negative_SC")
    public void postAnimalObjectWithWrongAttributesValuesTypesInBodyTest() {
        //Arrange differ body
        HashMap<String,Integer> wrongRequestBody = new HashMap();
        wrongRequestBody.put("age",00010);
        wrongRequestBody.put("name",00002);

        RestAssured
                .given()
                .body(wrongRequestBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(equalTo("{}"));

        // Test fails due to accepting different data types in body. Wrong schema validation in API implementation :(

    }
    @Test()
    @DisplayName(("Post Animal Object With Wrong Data Types"))
    @Tag("Negative_SC")
    public void postAnimalObjectWithWrongAttributesInBodyTest() {
        //Arrange differ body
        HashMap<String,String> wrongRequestBody = new HashMap();
        wrongRequestBody.put("wrong_age","test_age");
        wrongRequestBody.put("wrong_name","test_name");

        RestAssured
                .given()
                .body(wrongRequestBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(equalTo("{}"));

        // Test fails due to accepting different attribute in body. API has to be repaired absolutely :(

    }
    @Test()
    @Tag("Negative_SC")
    @DisplayName(("Post Animal Object With Empty Body"))
    public void postAnimalObjectWithEmptyBodyTest() {
        //Arrange differ body
        HashMap<String,String> emptyBody = new HashMap();

        RestAssured
                .given()
                .body(emptyBody)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(equalTo("{}"));

        // Test fails due to accepting empty body. API has to be improved absolutely :(
    }
}
