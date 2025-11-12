package basic.classfeature;

public class FatherClass {
    private int money;
    protected String name;
    protected int age;

    public FatherClass(){
        name = "FatherClass";
        age = 20;
        money = 100;
    }
    protected void print() {
        System.out.println("FatherClass: " + name + " " + age + " " + money);
    }
}
