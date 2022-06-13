package sse;

import java.util.stream.Stream;

public interface MessageEvent {

    public  void messageEvent(Stream message);

}
