package com.kevinkuai.mygl3D;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.kevinkuai.GLGame.GLScreen;
import com.kevinkuai.GLGame.HierObject;
import com.kevinkuai.GLGame.Texture;
import com.kevinkuai.GLGame.Vertices3;
import com.kevinkuai.framework.Game;

public class HierScreen extends GLScreen{
	public Vertices3 cube;
	Texture texture;
	HierObject sun;

	public HierScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		cube = creatCube();
		texture = new Texture(glGame, "crate.png", true);
		
		sun = new HierObject(cube,false);
		sun.z=-5;
		
		HierObject planet = new HierObject(cube,true);
		planet.x=3;
		planet.y=2;
		planet.scale = 0.3f;
		sun.children.add(planet);
		
		HierObject planet2 = new HierObject(cube,true);
		planet2.z=-9;
		planet.y=2;
		planet2.scale = 0.3f;
		sun.children.add(planet2);
		
		HierObject moon = new HierObject(cube,true);
		moon.x=1;
		moon.y=1;
		moon.scale=0.15f;
		planet.children.add(moon);
		
		HierObject moon2 = new HierObject(cube,true);
		moon2.z=-10;
		moon.y=1;
		moon2.scale=0.15f;
		planet2.children.add(moon2);
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
		sun.update(deltaTime);
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		GLU.gluPerspective(gl, 67, glGraphics.getWidth()/(float)glGraphics.getHeight(), 
				0.1f, 10);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 1, 5, 2, 0, 0, -5, 0, 1.5f, 0);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		texture.bind();
		cube.bind();
		
		sun.render(gl);
		
		cube.unbind();
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
