package concorrent.objectLock.guardSuspensionDesignPattern;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MailBox {
    private static int id = 1;
    private static Map<Integer, GuardedObject> boxes = new ConcurrentHashMap<>();

    public static GuardedObject get(int id){
        return boxes.remove(id);
    }

    public static synchronized GuardedObject createMailBox(){
        GuardedObject object = new GuardedObject();
        boxes.put(generateId(), object);
        return object;
    }

    private static int generateId(){
        return id++;
    }

    public static Set<Integer> getIds(){
        return boxes.keySet();
    }
}
