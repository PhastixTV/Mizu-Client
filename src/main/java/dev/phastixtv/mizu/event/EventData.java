package dev.phastixtv.mizu.event;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@AllArgsConstructor
public class EventData {

    public final Object source;
    public final Method target;
    public final byte priority;


}
