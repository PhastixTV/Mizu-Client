package dev.phastixtv.mizu.event;

import lombok.Setter;

import java.util.ArrayList;

public class Event {

    @Setter
    private boolean cancelled = false;

    public boolean isCancelled() {
        return cancelled;
    }

    public Event call() {
        final ArrayList<EventData> dataList = EventManager.get(this.getClass());

        if (dataList != null) {
            for (EventData data : dataList) {
                try {
                    data.target.invoke(data.source, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

}