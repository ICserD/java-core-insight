package basic.stringFeature;

public class TestStringFeature {
    public static void main(String[] args){
        String str1 = "hello, world!";
        String str2 = new String("hello, world!");
        String str3 = new String(str1);
        String str4 = new String("hello, world!").intern();


        System.out.println(str1 == str2);  // false
        System.out.println(str1 == str3);  // false
        System.out.println(str1.equals(str2));  //  true
        System.out.println(str1.equals(str3));  //  true
        System.out.println(str1 == str4);  //  true
        System.out.println(str1.equals(str4));  // true
    }
}