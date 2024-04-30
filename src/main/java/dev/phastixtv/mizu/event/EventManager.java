package dev.phastixtv.mizu.event;

import lombok.Getter;

import java.util.ArrayList;

public class EventManager {

    @Getter
    private static ArrayList<Event> eventList;

    public EventManager() {
        eventList = new ArrayList<>();
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public Event getEvent(Class<? extends Event> classModule) {
        for (Event event : eventList) {
            if (event.getClass().equals(classModule)) {
                return event;
            }
        }
        return null;
    }

}
