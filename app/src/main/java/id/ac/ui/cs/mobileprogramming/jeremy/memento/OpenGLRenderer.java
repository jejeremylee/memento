package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private double red = 1f;
    private double blue = 1f;
    private double green = 1f;

    private static final double DISCO_TIME = 5000;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor((float)red,(float)green,(float)blue,1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor((float)red,(float)green,(float)blue,1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        red = ((Math.sin(System.currentTimeMillis() *2 * Math.PI/DISCO_TIME)*0.5)+0.5);
        blue = ((Math.sin(System.currentTimeMillis() *2 * Math.PI/DISCO_TIME)*0.5)+0.5);
        green = ((Math.sin(System.currentTimeMillis() *2 * Math.PI/DISCO_TIME)*0.5)+0.5);
    }
}
