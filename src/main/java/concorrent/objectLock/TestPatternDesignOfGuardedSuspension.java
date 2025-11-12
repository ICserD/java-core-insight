package concorrent.objectLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPatternDesignOfGuardedSuspension {

    public static void main(String[] args) {
        GuardedObject guard = new GuardedObject();

        new Thread(() -> {
            log.info("{} 正在获取结果......", Thread.currentThread().getName());
            Object res = guard.get();
            log.info("{} 获取结果成功， 结果 : {}", Thread.currentThread().getName(), res);
        }, "Get Thread").start();

        new Thread(() -> {
            log.info("{} 准备睡会觉......", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.info("{} 准备开始产生结果", Thread.currentThread().getName());
            Object result = new Object();
            guard.complete(result);
            log.info("{} 产生结果 {}", Thread.currentThread().getName(), result);
        }, "Put Thread").start();
    }

}
