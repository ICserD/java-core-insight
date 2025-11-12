package concorrent.objectLock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class UsageOfWaitNotify {

    private final Hole hole = new Hole(8);

    private static boolean hasWater = false;

    private static boolean hasRice = false;

    @Data
    @AllArgsConstructor
    protected class Hole {
        int stones;
        public void moveStone(){
            if(stones >= 0) stones--;
            else log.info("石头已经搬完");
        }
    }

    public void example1() throws InterruptedException {
        new Thread(() -> {
            synchronized (hole){
                if(!hasWater) {
                    log.info("小浩等水来......");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(hasWater) {
                    log.info("小浩搬石头......， 当前石头个数 : {}", hole.getStones());
                    hole.moveStone();
                    log.info("搬完后......，当前石头个数 : {}", hole.getStones());
                }
            }
        }, "小浩").start();

        for(int i = 0; i < 7; i++){
            new Thread(() -> {
                synchronized (hole) {
                    log.info("{} 搬石头......, 当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                    hole.moveStone();
                    log.info("{} 搬完后......，当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                }
            }, "其他人_" + i).start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            log.info("老板送水来了......");
            hasWater = true;
        }, "老板").start();
    }


    /**
     * example 2
     * 场景:
     *  假设有 7 个人要搬石头，其中小浩每次搬石头之前都要等喝水，老板送水时间不确定
     *  由于石头在山洞，洞口很小，所以只能一次只能允许一个人去搬一个石头出来
     */
    public void example2() throws InterruptedException {
        new Thread(() -> {
            synchronized(hole){
                if(!hasWater) {
                    log.info("小浩等水来......");
                    try {
                        hole.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(hasWater) {
                    log.info("小浩搬石头......， 当前石头个数 : {}", hole.getStones());
                    hole.moveStone();
                    log.info("搬完后......，当前石头个数 : {}", hole.getStones());
                }
            }
        }, "小浩").start();

        for(int i = 0; i < 7; i++){
            new Thread(() -> {
                synchronized (hole) {
                    log.info("{} 搬石头......, 当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                    hole.moveStone();
                    log.info("{} 搬完后......，当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                }
            }, "其他人_" + i).start();
        }


        Thread.sleep(2000);
        new Thread(() -> {
            synchronized (hole) {
                log.info("老板送水来了......");
                hasWater = true;
                hole.notify();
            }
        }, "老板").start();
    }

    public void example3() throws InterruptedException {
        new Thread(() -> {
            synchronized(hole){
                if(!hasWater) {
                    log.info("小浩等水来......");
                    try {
                        hole.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(hasWater) {
                    log.info("小浩搬石头......， 当前石头个数 : {}", hole.getStones());
                    hole.moveStone();
                    log.info("搬完后......，当前石头个数 : {}", hole.getStones());
                }
            }
        }, "小浩").start();

        new Thread(() -> {
            synchronized(hole){
                if(!hasRice) {
                    log.info("小陈等饭来......");
                    try {
                        hole.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(hasRice) {
                    log.info("小陈搬石头......， 当前石头个数 : {}", hole.getStones());
                    hole.moveStone();
                    log.info("搬完后......，当前石头个数 : {}", hole.getStones());
                }
            }
        }, "小陈").start();

        for(int i = 0; i < 6; i++){
            new Thread(() -> {
                synchronized (hole) {
                    log.info("{} 搬石头......, 当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                    hole.moveStone();
                    log.info("{} 搬完后......，当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                }
            }, "其他人_" + i).start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (hole) {
                log.info("老板送水来了......");
                hasWater = true;
                hole.notifyAll();
            }
            try {
                Thread.sleep(1000);
                hasRice = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "老板").start();
    }

     public void example4() throws InterruptedException {
        new Thread(() -> {
            synchronized(hole){
                while(!hasWater) {
                    log.info("小浩等水来......");
                    try {
                        hole.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("小浩搬石头......， 当前石头个数 : {}", hole.getStones());
                hole.moveStone();
                log.info("搬完后......，当前石头个数 : {}", hole.getStones());
            }
        }, "小浩").start();

        new Thread(() -> {
            synchronized(hole){
                while(!hasRice) {
                    log.info("小陈等饭来......");
                    try {
                        hole.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("小陈搬石头......， 当前石头个数 : {}", hole.getStones());
                hole.moveStone();
                log.info("搬完后......，当前石头个数 : {}", hole.getStones());
            }
        }, "小陈").start();

        for(int i = 0; i < 6; i++){
            new Thread(() -> {
                synchronized (hole) {
                    log.info("{} 搬石头......, 当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                    hole.moveStone();
                    log.info("{} 搬完后......，当前石头个数 : {}", Thread.currentThread().getName(), hole.getStones());
                }
            }, "其他人_" + i).start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (hole) {
                log.info("老板送水来了......");
                hasWater = true;
                hole.notifyAll();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (hole){
                log.info("老板送饭来......");
                hasRice = true;
                hole.notifyAll();
            }

        }, "老板").start();
    }

}
