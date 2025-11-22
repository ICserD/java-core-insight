package concorrent.volatileUsage.twoStageStopDesignPattern;

import static java.lang.Thread.sleep;

public class TestTwoStageStopDesignPattern {
    public static void main(String[] args) {
        MonitorService monitorService = new MonitorService();
        monitorService.start();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        monitorService.stop();
    }
}
