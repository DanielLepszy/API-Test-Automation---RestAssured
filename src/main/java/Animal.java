import java.util.Objects;

public class Animal {

    int id;
    int age;
    String name;

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Animal(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for same object reference
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or different class
        Animal animal = (Animal) obj;
        return id==animal.id && age == animal.age && Objects.equals(name, animal.name);
    }
    @Override
    public String toString() {
        return "{ id='" + id + "', age=" + age + "', name=" + name + " }";
    }


}
