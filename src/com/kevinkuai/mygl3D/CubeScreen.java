package com.kevinkuai.mygl3D;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.kevinkuai.GLGame.GLScreen;
import com.kevinkuai.GLGame.Texture;
import com.kevinkuai.GLGame.Vertices3;
import com.kevinkuai.framework.Game;

public class CubeScreen extends GLScreen{
	Vertices3 cube;
	Texture texture;
	float angle=0;

	public CubeScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		cube = creatCube();
		texture = new Texture(glGame,"crate.png");
		
	}

	private Vertices3 creatCube() {
		// TODO Auto-generated method stub
		float[] vertices = {-0.5f,-0.5f,0.5f,0,1,
						    0.5f,-0.5f,0.5f,1,1,
						    0.5f,0.5f,0.5f,1,0,
						    -0.5f,0.5f,0.5f,0,0,
						    
						    0.5f,-0.5f,0.5f,0,1,
						    0.5f,-0.5f,-0.5f,1,1,
						    0.5f,0.5f,-0.5f,1,0,
						    0.5f,0.5f,0.5f,0,0,
						    
						    0.5f,-0.5f,-0.5f,0,1,
						    -0.5f,-0.5f,-0.5f,1,1,
						    -0.5f,0.5f,-0.5f,1,0,
						    0.5f,0.5f,-0.5f,0,0,
						    
						    -0.5f,-0.5f,-0.5f,0,1,
						    -0.5f,-0.5f,0.5f,1,1,
						    -0.5f,0.5f,0.5f,1,0,
						    -0.5f,0.5f,-0.5f,0,0,
						    
						    -0.5f,0.5f,0.5f,0,1,
						    0.5f,0.5f,0.5f,1,1,
						    0.5f,0.5f,-0.5f,1,0,
						    -0.5f,0.5f,-0.5f,0,0,
						    
						    -0.5f,-0.5f,0.5f,0,1,
						    0.5f,-0.5f,0.5f,1,1,
						    0.5f,-0.5f,-0.5f,1,0,
						    -0.5f,-0.5f,-0.5f,0,0};
		
		short[] indices = {0,1,3,1,2,3,
						   4,5,7,5,6,7,
						   8,9,11,9,10,11,
						   12,13,15,13,14,15,
						   16,17,19,17,18,19,
						   20,21,23,21,22,23};
		Vertices3 cube = new Vertices3(glGraphics, 24,36,false,true,false);
		cube.setVertices(vertices, 0, vertices.length);
		cube.setIndices(indices, 0, indices.length);
		
		return cube;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		angle += 45*deltaTime;
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl=glGraphics.getGL();
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		GLU.gluPerspective(gl, 67, glGraphics.getWidth()/(float)glGraphics.getHeight(), 
				0.1f, 10);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		cube.bind();
		gl.glTranslatef(0, 0, -3);
		gl.glRotatef(angle, 1, 1, 0);
		cube.draw(GL10.GL_TRIANGLES, 0, 36);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisable(GL10.GL_DEPTH_TEST);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
