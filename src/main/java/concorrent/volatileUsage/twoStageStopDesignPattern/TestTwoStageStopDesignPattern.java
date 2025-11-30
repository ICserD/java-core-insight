package concorrent.volatileUsage.twoStageStopDesignPattern;

import static java.lang.Thread.sleep;

public class TestTwoStageStopDesignPattern {
    public static void main(String[] args) {
        MonitorService monitorService = new MonitorService();

        for(int i = 0; i < 10; i++){
            new Thread(monitorService::start).start();
        }

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        monitorService.stop();
    }
}
