package event;

public class Event {

   String type;
   EventTarget target;


   public void Event( String type, EventTarget target ) {

      this.type = type;
      this.target = target;

   }

}
