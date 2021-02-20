package com.korochun.neutrino;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Neutrino {
    private final Game game;
    private Scene scene;
    private long frame;

    public Neutrino(Game game) {
        this.game = game;
    }

    public long getFrame() {
        return frame;
    }

    public void start() {
        if (!glfwInit()) return;
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        long window = glfwCreateWindow(800, 600, game.getTitle(), NULL, NULL);
        if (window == NULL) return;
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);
        glClearColor(0, 0, 0, 0);
        GLFWKeyCallback.create((window1, key, scancode, action, mods) -> game.key(this, key, action, mods)).set(window);
        game.init(this);
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            game.update(this);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            scene.render();
            glfwSwapBuffers(window);
            frame++;
        }

        for (Layer layer : Layer.layers.values()) {
            layer.destroy();
        }

        for (int imageHandle : Image.handles) {
            glDeleteTextures(imageHandle);
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public Neutrino setScene(Scene scene) {
        this.scene = scene;
        return this;
    }
}
