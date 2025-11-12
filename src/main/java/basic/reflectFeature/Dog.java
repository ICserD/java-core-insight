package basic.reflectFeature;

public class Dog {
    private String name;

    private int age;

    public String type;

    public String weight;

    public String height;

    public String sound(){
        return "汪汪汪";
    }

    private String poop(){
        return "poop";
    }

    public Dog(String name){
        this.name = name;
    }

    private Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Dog(){}
}
