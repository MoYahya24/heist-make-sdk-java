package sse;



import event.Event;
import event.EventTarget;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

public class EventSource extends EventTarget {

    public ReadyState readyState=ReadyState.CLOSED;
    public URL url;
    public HttpURLConnection connection;
    public String line;
   public StringBuffer content = new StringBuffer();

   URI uri;
   HttpClient client;
   HttpRequest request;

   Stream lines;



    public enum ReadyState{
        CONNECTING, OPEN, CLOSED
    }

    public EventSource(String URL) {
        try {
             uri = new URI(URL);
             client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
             request= HttpRequest.newBuilder(uri).timeout(Duration.ofSeconds(5)).GET().build();


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        }

/*

        try {
            this.url= new URL(URL);
            connection = (HttpURLConnection) url.openConnection();
            readyState=ReadyState.CONNECTING;
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    public void onerror( Event ev ) {

        /*if(connection.getResponseCode()>299) {
            ev.;
        }*/
    }

    public void onmessage( Event e ) {

        this.dispatchEvent(e);

    }

    public void close(){

        readyState=ReadyState.CLOSED;
    }

}
