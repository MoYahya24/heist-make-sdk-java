package sse;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collector;
import java.util.stream.Stream;


import javax.net.ssl.HttpsURLConnection;

public class EventSource extends EventTarget{

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
             client = HttpClient.newHttpClient();
             request= HttpRequest.newBuilder(uri).GET().build();


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



    public void onerror(Event ev) throws IOException {
        if(connection.getResponseCode()>299)
            ev.event();
    }

    public String onmessage(MessageEvent ev) throws IOException, InterruptedException {


        lines= client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        ev.messageEvent(lines);
        StringBuilder builder = new StringBuilder();
        lines.forEach(ch->builder.append(ch));
        return builder.toString();
    }

    public void close(){

        readyState=ReadyState.CLOSED;
    }

}
