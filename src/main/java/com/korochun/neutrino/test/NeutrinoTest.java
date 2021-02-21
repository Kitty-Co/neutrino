package com.korochun.neutrino.test;

import com.korochun.neutrino.*;

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
        Texture texture = new Texture("assets/tex.png").register("fire");
        neutrino.setScene(new Scene("test")
                .setBackground(0.02f, 0.28f, 0.78f)
                .addLayer(new ImageLayer("image", new AnimatedTexture(5,
                        texture.subImage(0, 0, 16, 16),
                        texture.subImage(0, 16, 16, 16),
                        texture.subImage(0, 32, 16, 16),
                        texture.subImage(0, 48, 16, 16),
                        texture.subImage(0, 64, 16, 16),
                        texture.subImage(0, 80, 16, 16),
                        texture.subImage(0, 96, 16, 16),
                        texture.subImage(0, 112, 16, 16)
                ))));
    }

    @Override
    public void key(Neutrino neutrino, int action, int key, int mods) {

    }

    @Override
    public void update(Neutrino neutrino) {

    }
}
