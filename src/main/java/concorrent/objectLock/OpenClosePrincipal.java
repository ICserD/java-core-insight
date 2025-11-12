package concorrent.objectLock;

import java.util.ArrayList;
import java.util.List;

public class OpenClosePrincipal {

    public class ThreadSafe {

        public void method1(){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < 100; i++){
                method2(list);
                method3(list);
            }
        }

        public void method2(List<Integer> list){
            list.add(1);
        }

        public void method3(List<Integer> list){
            list.remove(0);
        }
    }

    public class ThreadUnSafe extends ThreadSafe{

        @Override
        public void method3(List<Integer> list){
            new Thread(() -> {
                list.remove(0);
            }).start();
        }
    }

    public static void main(String[] args) {
    }
}
