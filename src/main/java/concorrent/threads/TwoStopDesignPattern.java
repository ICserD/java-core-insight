package concorrent.threads;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

@Slf4j
public class TwoStopDesignPattern {

    public static void main(String[] args) {
        Thread monitorTaskThread = new Thread(() -> {
           while(true){
               if(currentThread().isInterrupted()){
                    log.info("料理后事......");
                    break;  //  退出循环, 结束监控
               }
               try{
                   sleep(2000);
                   log.info("检测并记录信息......");
               } catch (InterruptedException e) {
                   log.info("睡眠期间被中断......当前标志位被重置为: {}, 需要处理标志位......", currentThread().isInterrupted());
                   currentThread().interrupt();  //  重新设置打断标记
               }
           }
        }, "MonitorThread");
        monitorTaskThread.start();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("主线程在睡眠时中断检测线程......");
        monitorTaskThread.interrupt();
        log.info("当前时间戳: {}", System.currentTimeMillis());

    }
}
