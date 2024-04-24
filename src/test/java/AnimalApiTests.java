import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;

public class AnimalApiTests {

    Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "https://retoolapi.dev/";
    }

    @Test
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
                    .log().all()
                .when()
                    .get("Zaf5SG/animals")
                .then()
                    .statusCode(200)
                    .extract().asString();

        List<Animal> animalsObjectList = gson.fromJson(response, userListType); // Map JSON to Animal object

        //Assert
        Assert.assertTrue(expectedAnimalsObjectList.equals(animalsObjectList));
    }

    @Test()
    public void postNewAnimalTest() {
        //CRUD operations

        Animal animal = new Animal( 4,19, "Harold");

        RestAssured
                .given().log().all()
                    .body(animal)
                    .contentType(ContentType.JSON)
                .when()
                    .post("Zaf5SG/animals")
                .then()
                    .statusCode(201)
                .body("id",equalTo(4));

        Animal retrievedAnimalResponse =
                RestAssured
                .given().log().all()
                .when()
                    .get("Zaf5SG/animals/"+animal.getId())
                .then()
                    .statusCode(200)
                    .extract().response()
                    .body().as(Animal.class); //Map to Animal model class


        Assert.assertTrue(animal.equals(retrievedAnimalResponse));

        animal.setAge(99);
        animal.setName("UpdatedName");

        RestAssured
                .given().log().all()
                    .body(animal)
                    .contentType(ContentType.JSON)
                .when()
                    .put("Zaf5SG/animals/"+ animal.getId())
                .then()
                    .statusCode(200)
                    .body("id",equalTo(animal.getId()));


   Animal updatedAnimalResponse =
                RestAssured
                        .given().log().all()
                        .when()
                            .get("Zaf5SG/animals/"+animal.getId())
                        .then()
                            .statusCode(200)
                            .extract().response()
                            .body().as(Animal.class); //Map to Animal model class

        Assert.assertTrue(animal.equals(updatedAnimalResponse));

        //Delete
        RestAssured
                .given().log().all()
                    .when()
                    .delete("Zaf5SG/animals/"+animal.getId())
                .then()
                .statusCode(200);
    }
}

