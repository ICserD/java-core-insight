package concorrent.objectLock;

public class GuardedObject {
    private Object response;

    public synchronized Object get(){
        while(response == null){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    public synchronized void complete(Object response){
        this.response = response;
        this.notifyAll();
    }
}
