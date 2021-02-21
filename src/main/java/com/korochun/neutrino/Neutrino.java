package com.korochun.neutrino;

import org.joml.Matrix3x2f;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Neutrino {
    static {
        System.setProperty("java.awt.headless", "true");
    }

    private final Game game;
    private Scene scene;
    private long frame;
    private int program;

    public Neutrino(Game game) {
        this.game = game;
    }

    public long getFrame() {
        return frame;
    }

    public void start() {
        // Initialize GLFW
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // Create new window
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_NATIVE_CONTEXT_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_ANY_PROFILE);
        long window = glfwCreateWindow(800, 600, game.getTitle(), NULL, NULL);
        if (window == NULL) throw new IllegalStateException("Unable to open window");
        GLFWKeyCallback.create((window1, key, scancode, action, mods) -> game.key(this, key, action, mods)).set(window);

        // Setup OpenGL
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Create the shader program
        program = glCreateProgram();
        int vertex = glCreateShader(GL_VERTEX_SHADER), fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertex, "",
                "#version 110\n",
                "",
                "attribute vec2 pos;",
                "uniform mat3 mat;",
                "varying vec2 texCoord;",
                "",
                "void main() {",
                "   vec3 p = mat * vec3(pos, 1.0);", // FIXME: Rotation transformation doesn't preserve angles
                "   gl_Position = vec4(p.x * 2.0 - 1.0, 1.0 - p.y * 2.0, 0.0, 1.0);",
                "   texCoord = pos;",
                "}"
        );
        glShaderSource(fragment, "",
                "#version 110\n",
                "",
                "uniform sampler2D sampler;",
                "uniform vec2 uv0, uv1;",
                "varying vec2 texCoord;",
                "",
                "void main() {",
                "   vec4 color = texture2D(sampler, texCoord * (uv1 - uv0) + uv0);",
                "   if (color.a == 0.0) discard;",
                "   gl_FragColor = color;",
                "}"
        );
        glCompileShader(vertex);
        System.out.println(glGetShaderInfoLog(vertex));
        System.out.println(glGetShaderInfoLog(fragment));
        glCompileShader(fragment);
        glAttachShader(program, vertex);
        glAttachShader(program, fragment);
        glLinkProgram(program);
        System.out.println(glGetProgramInfoLog(program));
        glDetachShader(program, vertex);
        glDetachShader(program, fragment);
        glDeleteShader(vertex);
        glDeleteShader(fragment);

        // Run the game
        game.init(this);
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            game.update(this);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            scene.render(this);
            glfwSwapBuffers(window);
            frame++;
        }

        // Clean up
        glDeleteProgram(program);
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

    protected Neutrino draw(Matrix3x2f matrix, Image texture, int buffer, int n) {
        // Use the shader program
        glUseProgram(program);
        glEnableVertexAttribArray(glGetAttribLocation(program, "pos"));
        glActiveTexture(GL_TEXTURE0);

        // Set uniforms
        Rectangle uv = texture.getUV(this);
        glUniformMatrix3fv(glGetUniformLocation(program, "mat"), false, matrix.get3x3(new float[9]));
        glUniform1i(glGetUniformLocation(program, "sampler"), 0);
        glUniform2f(glGetUniformLocation(program, "uv0"), uv.x0, uv.y0);
        glUniform2f(glGetUniformLocation(program, "uv1"), uv.x1, uv.y1);

        // Set the vertex buffer
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glVertexAttribPointer(glGetAttribLocation(program, "pos"), 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Draw
        glBindTexture(GL_TEXTURE_2D, texture.getHandle(this));
        glDrawArrays(GL_TRIANGLES, 0, n);
        glBindTexture(GL_TEXTURE_2D, 0);

        // Clean up
        glDisableVertexAttribArray(glGetAttribLocation(program, "pos"));
        glUseProgram(0);

        return this;
    }
}
