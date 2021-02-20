package com.korochun.neutrino;

public class AnimatedTexture extends Image {
    private final int fps;
    private final Texture[] frames;

    public AnimatedTexture(int fps, Texture... image) {
        this.fps = fps;
        frames = image;
    }

    protected int getHandle(Neutrino neutrino) {
        return frames[(int) (neutrino.getFrame() / fps) % frames.length].getHandle(neutrino);
    }

    protected Rectangle getUV(Neutrino neutrino) {
        return frames[(int) (neutrino.getFrame() / fps) % frames.length].getUV(neutrino);
    }
}
