package basic.datafeature.boxing;

public class TestInOutBox {
    public static void main(String[] args){
        //  自动装箱
        Integer a1 = 10;

        //  自动拆箱
        int b1 = a1;

        //  非自动装箱
        Integer a2 = Integer.valueOf(10);

        //  非自动拆箱
        int b2 = a2.intValue();
    }
}
