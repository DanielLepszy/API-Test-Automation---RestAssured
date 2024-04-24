package TestUtils;

import Models.Animal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Mapper {
    protected Gson gson;

    public List<Animal> mapResponeToAnimalList(String response){
        Type userListType = new TypeToken<List<Animal>>() {}.getType();
        return gson.fromJson(response, userListType); // Map JSON to Models.Animal object
    }

    public Mapper() {
     this.gson = new Gson();
    }
}
