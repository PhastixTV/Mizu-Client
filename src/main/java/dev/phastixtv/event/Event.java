package dev.phastixtv.event;

import lombok.Setter;

public class Event {

    @Setter
    private boolean cancelled = false;

    public boolean isCancelled() {
        return cancelled;
    }

}