package com.korochun.neutrino;

public interface Game {
    String getTitle();

    void init(Neutrino neutrino);

    void key(Neutrino neutrino, int action, int key, int mods);

    void update(Neutrino neutrino);
}