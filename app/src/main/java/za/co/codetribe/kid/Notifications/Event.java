package za.co.codetribe.kid.notifications;

/**
 * Created by Codetribe on 2017/09/01.
 */

public class Event {


        private String eventName;
        private String eventDiscription;

    public Event()
    {

    }

    public Event(String eventName, String eventDiscription) {
        this.eventName = eventName;
        this.eventDiscription = eventDiscription;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDiscription(String eventDiscription) {
        this.eventDiscription = eventDiscription;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDiscription() {
        return eventDiscription;
    }
}

