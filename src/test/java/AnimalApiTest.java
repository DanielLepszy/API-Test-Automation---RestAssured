import Models.Animal;
import TestUtils.ClearDataScript;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.lang.reflect.Type;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;

public class AnimalApiTest {

    /**
     * Before suite test run - prepare RestAssured configuration. E.x set detailed logs only when test fail.
     * **/

    Gson gson = new Gson();
    @BeforeClass
    public static void setUpConf(){
        RestAssured.baseURI = "https://retoolapi.dev/";
        RestAssured.basePath = "Zaf5SG/animals/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Rollback all changes before each test. It makes each test case independent
     * **/
    @Before
    public void setUp() throws Exception {
        ClearDataScript.rollbackDataToInitialDataStage(gson);
    }

    @Test
    @DisplayName(("Get All Animals"))
    @Tag("First")
    public void getAnimalsTest() {

        //Arrange
        Type userListType = new TypeToken<List<Animal>>() {}.getType();
        List<Animal> expectedAnimalsObjectList = new ArrayList<>(Arrays.asList(
                new Animal(1,19, "Marinna"),
                new Animal( 2,9, "Erhart"),
                new Animal( 3, 1, "Tom")));
        //Act
        String response = RestAssured
                .given()
                    
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .extract().asString();

        List<Animal> animalsObjectList = gson.fromJson(response, userListType); // Map JSON to Models.Animal object

        //Assert
        Assert.assertTrue(expectedAnimalsObjectList.equals(animalsObjectList));
    }

    @Test()
    @DisplayName(("Full CRUD operation"))
    public void postNewAnimalTest() {
        //CRUD operations
        Animal animal = new Animal( 4,19, "Harold");
        RestAssured
                .given()
                    .body(animal)
                    .contentType(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                .body("id",equalTo(4));

        Animal retrievedAnimalResponse =
                RestAssured
                .given()
                .when()
                    .get(animal.getStringId())
                .then()
                    .statusCode(200)
                    .extract().response()
                    .body().as(Animal.class); //Map to Models.Animal model class


        Assert.assertTrue(animal.equals(retrievedAnimalResponse));

        animal.setAge(99);
        animal.setName("UpdatedName");

        RestAssured
                .given()
                    .body(animal)
                    .contentType(ContentType.JSON)
                .when()
                    .put(animal.getStringId())
                .then()
                    .statusCode(200)
                    .body("id",equalTo(animal.getId()));


   Animal updatedAnimalResponse =
                RestAssured
                        .given()
                        .when()
                            .get(animal.getStringId())
                        .then()
                            .statusCode(200)
                            .extract().response()
                            .body().as(Animal.class); //Map to Models.Animal model class

        Assert.assertTrue(animal.equals(updatedAnimalResponse));

        //Delete previous created object
        RestAssured
                .given()
                    .when()
                    .delete(animal.getStringId())
                .then()
                .statusCode(200);
   }


    @Test()
    @DisplayName(("Get Non Existed Object By ID"))
    @Tag("negative")
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
    @Tag("negative")
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
    @Tag("negative")
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
    @Tag("negative")
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
    @Tag("negative")
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
    @Tag("negative")
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

        // Test fails due to accepting empty body. API has to be repaired absolutely :(

    }
}

