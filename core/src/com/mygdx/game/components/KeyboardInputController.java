package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;

/**
 * Determines keybindings for certain actions, and specifies keyboard control over an entity
 * Created by isaac on 10/1/15.
 */
public class KeyboardInputController implements Component {
    public HashMap<Integer, Action> keyBinds;

    public KeyboardInputController() {
        keyBinds = new HashMap<>();
    }

    public KeyboardInputController(HashMap<Integer, Action> kb) {
        keyBinds = kb;
    }

    public void addKeyBind(int keyCode, Action action) {
        keyBinds.put(keyCode, action);
    }

    public void changeKeyBind(int oldKeyCode, int newKeyCode) {
        Action a = keyBinds.get(oldKeyCode);
        keyBinds.remove(oldKeyCode);
        keyBinds.put(newKeyCode, a);
    }
}