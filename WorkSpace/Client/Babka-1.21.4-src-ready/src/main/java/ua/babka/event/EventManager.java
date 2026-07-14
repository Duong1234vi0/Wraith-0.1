package ua.babka.event;

import ua.babka.Babka;
import ua.babka.event.Event;

public class EventManager {
    public static void post(Event event) {
        Babka.getInstance().getEventBus().post((Object)event);
    }
}

