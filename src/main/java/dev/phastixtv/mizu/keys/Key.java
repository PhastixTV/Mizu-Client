package dev.phastixtv.mizu.keys;

import lombok.Getter;
import lombok.Setter;

public interface Key {

    int getKey();

    String getName();

    void releaseAction();
    void pressAction();
    void holdAction();
    default void onScrollUp() {

    }
    default void onScrollDown() {

    }

    boolean isPressed();
    void setPressed(boolean pressed);
}
