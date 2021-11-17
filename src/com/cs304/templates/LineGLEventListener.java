package com.cs304.templates;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class LineGLEventListener implements GLEventListener {

  //public slope and (a,b) for setting up the line
  public double m = -1;
  public double a = 3;
  public double b = 2;
  //floats used for color selection
  float red;
  float green;
  float blue;

  @Override
  public void init(GLAutoDrawable glAutoDrawable) {
    GL gl = glAutoDrawable.getGL();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glLineWidth(2.0f);
    gl.glPointSize(2.0f);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(-250.0, 250.0, -150.0, 150.0, -1, 1);
  }

  @Override
  public void display(GLAutoDrawable glAutoDrawable) {
    GL gl = glAutoDrawable.getGL();
    drawGraph(gl);
    //This is the new code. We find out
//which trig function is selected,
//then we draw a scaled up version of
//the function.
//Let's make the line red
    red = 1.0f;
    green = 0.2f;
    blue = 0.2f;
    gl.glColor3f(red, green, blue);
// Point-slope form of a line is:
// y = m(x -a) + b where (a,b) is the
// point.
// Also,
// y - b = m( x - a )
// works.
// m is of course the slope.
//Let's make the line
    gl.glBegin(GL.GL_POINTS);
//let's make every grid one point even
//though it is made by 10 x 10 pixels.
    double a1 = a * 10;
    double b1 = b * 10;
    for (int x = -250; x <= 250; x++) {
      gl.glVertex2d(x, (m * (x - a1) + b1));
    }
    gl.glEnd();
//Let's make the point now
//making the point white
    red = 1.0f;
    green = 1.0f;
    blue = 1.0f;
    gl.glColor3f(red, green, blue);
    gl.glPointSize(4.0f);
    gl.glBegin(GL.GL_POINTS);
    gl.glVertex2d(a1, b1);
    gl.glEnd();
//resetting the point to 2 pixels.
    gl.glPointSize(2.0f);
  }

  @Override
  public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

  }

  @Override
  public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

  }

  private void drawGraph(GL gl) {
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
////////////////////
//drawing the grid
    red = 0.2f;
    green = 0.2f;
    blue = 0.2f;
    gl.glColor3f(red, green, blue);
//You may notice I'm using GL_LINES here.
//Details of glBegin() will be discussed latter.
    gl.glBegin(GL.GL_LINES);
//draw the vertical lines
    for (int x = -250; x <= 250; x += 10) {
      gl.glVertex2d(x, -150);
      gl.glVertex2d(x, 150);
    }
//draw the horizontal lines
    for (int y = -150; y <= 150; y += 10) {
      gl.glVertex2d(-250, y);
      gl.glVertex2d(250, y);
    }
    gl.glEnd();
//////////////////////////////
// draw the x-axis and y-axis
    red = 0.0f;
    green = 0.2f;
    blue = 0.4f;
    gl.glColor3f(red, green, blue);
    gl.glBegin(GL.GL_LINES);//line for y-axis
    gl.glVertex2d(0, 140);
    gl.glVertex2d(0, -140);
//line for x-axis
    gl.glVertex2d(240, 0);
    gl.glVertex2d(-240, 0);
    gl.glEnd();
/////////////////////
// draw arrow heads
    gl.glBegin(GL.GL_TRIANGLES);
    gl.glVertex2d(0, 150);
    gl.glVertex2d(-5, 140);
    gl.glVertex2d(5, 140);
    gl.glVertex2d(0, -150);
    gl.glVertex2d(-5, -140);
    gl.glVertex2d(5, -140);
    gl.glVertex2d(250, 0);
    gl.glVertex2d(240, -5);
    gl.glVertex2d(240, 5);
    gl.glVertex2d(-250, 0);
    gl.glVertex2d(-240, -5);
    gl.glVertex2d(-240, 5);
    gl.glEnd();
  }
}
