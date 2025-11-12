package basic.reflectFeature;

import java.lang.reflect.Constructor;

public class ReflectOfConstructor {

    public static void getAndUseConstructor(){
        try {
            Class dogClass = Class.forName("basic.reflectFeature.Dog");
            Constructor[] dogConstructors = dogClass.getConstructors();
            System.out.println("Constructors below: ");
            for (Constructor dogConstructor : dogConstructors) {
                System.out.println(dogConstructor);
            }

            //  declared constructors 是获取声明过的所有构造方法，包括 private
            Constructor[] dogDeclaredConstructors = dogClass.getDeclaredConstructors();
            System.out.println("Declared Constructors below: ");
            for (Constructor dogDeclaredConstructor : dogDeclaredConstructors) {
                System.out.println(dogDeclaredConstructor);
            }

            Constructor dogConstructor = dogClass.getConstructor(String.class);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
