package com.korochun.neutrino;

public class AnimatedTexture extends Image {
    private final int delay;
    private final Texture[] frames;

    public AnimatedTexture(int delay, Texture... image) {
        this.delay = delay;
        frames = image;
    }

    protected int getHandle(Neutrino neutrino) {
        return frames[(int) (neutrino.getFrame() / delay) % frames.length].getHandle(neutrino);
    }

    protected Rectangle getUV(Neutrino neutrino) {
        return frames[(int) (neutrino.getFrame() / delay) % frames.length].getUV(neutrino);
    }

    public AnimatedTexture register(String name) {
        return (AnimatedTexture) super.register(name);
    }
}
