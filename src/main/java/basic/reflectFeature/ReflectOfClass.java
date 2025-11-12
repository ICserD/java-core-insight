package basic.reflectFeature;

public class ReflectOfClass {
    public static void waysOfGetClassInfo() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class dogClass = Class.forName("basic.reflectFeature.Dog");
        Dog dog = (Dog)dogClass.newInstance();
        System.out.println("dog class 1: " + dogClass);
        System.out.println("method invoke: " + dog.sound());

        dogClass = Dog.class;
        System.out.println("dog class 2: " + dogClass);

        dogClass = dog.getClass();
        System.out.println("dog class 3: " + dogClass);
    }
}
