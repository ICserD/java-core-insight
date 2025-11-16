package concorrent.objectLock.guardSuspensionDesignPattern;

public class GuardedObject {
    private Object response;

    public synchronized Object get() throws InterruptedException{
        return get(0);
    }

    public synchronized Object get(long timeout) throws InterruptedException {
        long startTime = System.currentTimeMillis(), passTime = 0;
        if(timeout == 0){
            while(response == null){
                this.wait(0);
            }
            return response;
        } else {
            while (response == null) {
                if (passTime >= timeout) break;
                this.wait(timeout - passTime);
                passTime = System.currentTimeMillis() - startTime;
            }
            return response;
        }
    }

    public synchronized void complete(Object response){
        this.response = response;
        this.notifyAll();
    }
}
