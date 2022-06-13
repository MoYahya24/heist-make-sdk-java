package sseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sse.EventSource;

import java.io.IOException;


public class EventSourceTest {

    String str;
    EventSource eventSource= new EventSource("http://localhost:8080/stream");
    @Test
    void name() {
        try {
            eventSource.close();
            Assertions.assertEquals("data: Yahya!\n" +
                    "\n" +
                    "data: this is data\n" +
                    "\n" +
                    "data: I am a message", eventSource.onmessage((content)-> content.forEach(System.out::println)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
