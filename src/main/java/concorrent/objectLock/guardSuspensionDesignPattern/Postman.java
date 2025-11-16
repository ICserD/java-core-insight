package concorrent.objectLock.guardSuspensionDesignPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Postman extends Thread{
    private int mailId;
    private String mail;

    @Override
    public void run(){
        log.info("{} 正在获取邮箱, 邮箱编号为 : {}", Thread.currentThread().getName(), mailId);
        GuardedObject box = MailBox.get(mailId);
        StringBuilder sb = new StringBuilder();
        sb.append("送信小哥 : ").append(Thread.currentThread().getName())
                .append(" 信件 id : ").append(mailId)
                .append(" 信件内容 : ").append(mail);
        box.complete(sb.toString());
    }
}
