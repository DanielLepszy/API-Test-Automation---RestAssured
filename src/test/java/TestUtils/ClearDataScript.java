package TestUtils;

import Models.Animal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClearDataScript {

    static ArrayList<Animal> initialDataStateList = new ArrayList<>(Arrays.asList(
                new Animal(1,19, "Marinna"),
                new Animal( 2,9, "Erhart"),
                new Animal( 3, 1, "Tom")));

    /**
     * This test is dedicated to clearing data to initial stage. It's good to have it just in case
     */

    public static void rollbackDataToInitialDataStage(Gson gson) throws Exception {
        Type userListType = new TypeToken<List<Animal>>() {}.getType();
        String url = "https://retoolapi.dev/Zaf5SG/animals/";
        String response = RestAssured
                .given()
                .log().ifValidationFails()
                .when()
                    .get(url)
                .then()
                    .statusCode(200)
                    .extract().asString();

        List<Animal> animalsObjectList = gson.fromJson(response, userListType); // Map JSON to Animal model

        if(!animalsObjectList.isEmpty()){
            for(Animal animal: animalsObjectList){
                if(!initialDataStateList.contains(animal))
                {
                    RestAssured
                            .given()
                                .log().all()
                            .when()
                                .delete(url.concat(animal.getStringId()))
                            .then()
                                .statusCode(200);
                }
            }
        } else {
            throw new Exception("There are no objects from API");
        }
    }
}
