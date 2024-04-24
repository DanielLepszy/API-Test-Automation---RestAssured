package TestBase;

import Models.Animal;
import TestUtils.Mapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;

public class TestsBase extends Mapper {

    public static void baseConfigSetup(){
        RestAssured.baseURI = "https://retoolapi.dev/";
        RestAssured.basePath = "Zaf5SG/animals/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
    public String getAllAnimalObjects(){
        return RestAssured
                .given()
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .extract().asString();
    }
    public Animal getAnimalObjectsById(String id){
            return RestAssured
                        .given()
                        .when()
                            .get(id)
                        .then()
                            .statusCode(200)
                            .extract().response()
                            .body().as(Animal.class); //Map to Models.Animal model class
    }
    public void updateAnimalObjectsBy(Animal animalObjectToUpdate){
             RestAssured
                    .given()
                        .body(animalObjectToUpdate)
                        .contentType(ContentType.JSON)
                    .when()
                        .put(animalObjectToUpdate.getStringId())
                    .then()
                        .statusCode(200)
                        .body("id",equalTo(animalObjectToUpdate.getId()));

    }
    public void deleteAnimalObjectById(String id) {
        RestAssured
                .given()
                .when()
                .delete(id)
                .then()
                .statusCode(200);
    }
    public String postAnimalObject(Animal animal) {
        int id = RestAssured
                .given()
                    .body(animal)
                    .contentType(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                    .extract()
                    .path("id");

        return String.valueOf(id);
    }
}
