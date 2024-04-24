import Models.Animal;
import TestBase.TestsBase;
import TestUtils.ClearDataScript;
import org.junit.jupiter.api.*;

@Tag("CRUD")
public class AnimalApiTest extends TestsBase {

    /**
     * Before suite test run - prepare RestAssured configuration. E.x set detailed logs only when test fail.
     * **/

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
    @DisplayName(("Full CRUD operation"))
    public void postNewAnimalTest() {

        //CRUD operations
        Animal animal = new Animal( 4,19, "Harold");
        String id = postAnimalObject(animal);

        Animal retrievedAnimalResponse = getAnimalObjectsById(id);

        Assertions.assertTrue(animal.equals(retrievedAnimalResponse));

        //Update
        animal.setAge(99);
        animal.setName("UpdatedName");
        updateAnimalObjectsBy(animal);

        Animal updatedAnimalResponse = getAnimalObjectsById(id);

        Assertions.assertTrue(animal.equals(updatedAnimalResponse));

        //Delete previous created object
        deleteAnimalObjectById(id);
   }



}

