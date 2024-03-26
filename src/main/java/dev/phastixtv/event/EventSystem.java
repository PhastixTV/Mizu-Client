package dev.phastixtv.event;

import java.util.ArrayList;

public class EventSystem {

    private static ArrayList<Event> events;

    public EventSystem() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }
}
