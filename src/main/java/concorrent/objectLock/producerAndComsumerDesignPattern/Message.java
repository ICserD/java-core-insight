package concorrent.objectLock.producerAndComsumerDesignPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Message {
    private int id;
    private Object message;
}
