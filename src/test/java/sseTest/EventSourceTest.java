package sseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sse.EventSource;

import java.io.IOException;


public class EventSourceTest {

    String str;

    @Test
    void name() throws IOException, InterruptedException {
        EventSource eventSource= new EventSource("http://localhost:8080/stream");


            eventSource.addEventListener("message", );


    }
}
