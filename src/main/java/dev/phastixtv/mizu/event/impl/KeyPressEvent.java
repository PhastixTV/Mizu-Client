package dev.phastixtv.mizu.event.impl;

import dev.phastixtv.mizu.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyPressEvent extends Event {
    private int key;
    private int scanCode;
    private int action;
}
