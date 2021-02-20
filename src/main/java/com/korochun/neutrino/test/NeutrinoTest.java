package com.korochun.neutrino.test;

import com.korochun.neutrino.Game;
import com.korochun.neutrino.Neutrino;
import com.korochun.neutrino.Scene;

public class NeutrinoTest implements Game {
    public static void main(String[] args) {
        new Neutrino(new NeutrinoTest()).start();
    }

    @Override
    public String getTitle() {
        return "Neutrino Test";
    }

    @Override
    public void init(Neutrino neutrino) {
        neutrino.setScene(new Scene("test").setBackground(0.02f, 0.28f, 0.78f));
    }

    @Override
    public void key(Neutrino neutrino, int action, int key, int mods) {

    }

    @Override
    public void update(Neutrino neutrino) {

    }
}
