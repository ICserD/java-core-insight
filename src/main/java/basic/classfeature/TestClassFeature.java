package basic.classfeature;

public class TestClassFeature {
    public static void main(String[] args){
        //  测试数据转型
        //  自动转型（向上转型）
        FatherClass newF = new SonClass();

        //  向上转型
        SonClass newS = (SonClass) newF;

        newS.print();

    }
}
