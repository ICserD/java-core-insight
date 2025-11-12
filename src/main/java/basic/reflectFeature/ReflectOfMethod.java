package basic.reflectFeature;

import java.lang.reflect.Method;

public class ReflectOfMethod {
    public static void getAndUseMethod(){
        try {
            Class dogClass = Class.forName("basic.reflectFeature.Dog");
            Method[] methods = dogClass.getMethods();
            System.out.println("Methods below: ");
            for (Method method : methods) {
                System.out.println( method);
            }

            Method[] declaredMethods = dogClass.getDeclaredMethods();
            System.out.println("Declared Methods below: ");
            for (Method declaredMethod : declaredMethods) {
                System.out.println(declaredMethod);
            }

            Method soundMethod = dogClass.getMethod("sound");
            Dog dogInstance = (Dog)dogClass.newInstance();
            System.out.println(soundMethod.invoke(dogInstance));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
