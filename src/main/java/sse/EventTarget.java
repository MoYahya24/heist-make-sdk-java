package sse;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTarget {

    public Map<String, List<EventListener>> listeners = new HashMap<>();



    public void addEventListener(String type, EventListener listener ){
        if(!listeners.containsKey(type))
            listeners.put(type, new ArrayList<>());
        listeners.get(type).add(listener);
    }

    public void removeListener(String type, EventListener listener ){
        listeners.get(type).remove(listener);
    }
}
