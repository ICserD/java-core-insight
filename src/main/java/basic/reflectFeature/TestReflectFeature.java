package basic.reflectFeature;

public class TestReflectFeature {
    public static void main(String[] args) {
        try {
//            ReflectOfClass.waysOfGetClassInfo();
//            ReflectOfConstructor.getAndUseConstructor();
            ReflectOfMethod.getAndUseMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
