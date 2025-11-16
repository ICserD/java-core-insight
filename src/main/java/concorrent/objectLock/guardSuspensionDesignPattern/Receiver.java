package concorrent.objectLock.guardSuspensionDesignPattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Receiver extends Thread{
    @Override
    public void run(){
        GuardedObject box = MailBox.createMailBox();
        try {
            log.info("{} 正在等待获取邮件......", Thread.currentThread().getName());
            Object mail = box.get(1000);
            log.info("{} 获取邮件的内容是 : {}", Thread.currentThread().getName(), mail);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
