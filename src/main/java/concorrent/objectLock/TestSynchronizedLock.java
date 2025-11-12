package concorrent.objectLock;

public class TestSynchronizedLock {

    public static void main(String[] args) {
//        UpgradeOfSynchronized upgradeOfSynchronized = new UpgradeOfSynchronized(new UpgradeOfSynchronized.Resource());
//        upgradeOfSynchronized.alternativelyExecute();

        UsageOfWaitNotify usageOfWaitNotify = new UsageOfWaitNotify();
        try {
            usageOfWaitNotify.example4();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
