package dev.phastixtv.event;

import lombok.Getter;

import java.util.ArrayList;

public class EventSystem {

    @Getter
    private static ArrayList<Event> events;

    public EventSystem() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

}
