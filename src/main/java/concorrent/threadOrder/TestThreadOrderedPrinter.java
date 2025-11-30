package concorrent.threadOrder;

public class TestThreadOrderedPrinter {
    public static void main(String[] args) {
        OrderedPrintUsingWaitNotify orderedPrintUsingWaitNotify = new OrderedPrintUsingWaitNotify(1, 3);

        Thread tPrintA = new Thread(() -> {
            orderedPrintUsingWaitNotify.print("A", 1, 2);
        }, "Thread A");
         Thread tPrintB = new Thread(() -> {
            orderedPrintUsingWaitNotify.print("B", 2, 3);
        }, "Thread B");
         Thread tPrintC = new Thread(() -> {
            orderedPrintUsingWaitNotify.print("C", 3, 1);
        }, "Thread C");

         tPrintA.start();
         tPrintB.start();
         tPrintC.start();
    }
}
