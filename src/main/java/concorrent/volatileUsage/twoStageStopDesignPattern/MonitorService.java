package concorrent.volatileUsage.twoStageStopDesignPattern;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class MonitorService {
    private volatile boolean stop = false;

    private Thread monitorWorker;

    private boolean starting;

    public void start(){
        if(starting) return;
        synchronized (this) {
            if(starting) return;
            starting = true;
        }
        monitorWorker = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.info("stop......");
                    log.info("deal with something last......");
                    break;
                }

                try {
                    log.info("working......");
                    sleep(2000);
                } catch (InterruptedException e) {
                    log.info("wake up.....");
                }
            }
        }, "Monitor");
        monitorWorker.start();
    }

    public void stop(){
        stop = true;
        log.info("param stop change to true......");
        monitorWorker.interrupt();
    }
}
