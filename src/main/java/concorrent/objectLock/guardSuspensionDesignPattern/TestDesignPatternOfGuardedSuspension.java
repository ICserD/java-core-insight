package concorrent.objectLock.guardSuspensionDesignPattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDesignPatternOfGuardedSuspension {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Receiver().start();
        }

        for(int id : MailBox.getIds()){
            Postman postman = new Postman(id, String.valueOf("信件内容_" + id));
            postman.start();
        }
    }
}
