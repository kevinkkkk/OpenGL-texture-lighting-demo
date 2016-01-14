package com.kevinkuai.mygl3D;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.kevinkuai.GLGame.AmbientLight;
import com.kevinkuai.GLGame.DirectionalLight;
import com.kevinkuai.GLGame.GLScreen;
import com.kevinkuai.GLGame.Material;
import com.kevinkuai.GLGame.PointLight;
import com.kevinkuai.GLGame.Texture;
import com.kevinkuai.GLGame.Vertices3;
import com.kevinkuai.framework.Game;

public class LightScreen extends GLScreen{
	float angle;
	Vertices3 cube;
	Texture texture;
	AmbientLight ami;
	PointLight poi;
	DirectionalLight dir;
	Material mat;

	public LightScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		cube = createCube();
		texture = new Texture(glGame, "crate.png");
		ami = new AmbientLight();
		ami.setColor(0,0.2f,0,1);
		poi = new PointLight();
		poi.setDiffuse(1, 0, 0, 1);
		poi.setPosition(3, 3, 0);
		dir = new DirectionalLight();
		dir.setDiffuse(0, 0, 1, 1);
		dir.setDirection(1, 0, 0);
		mat = new Material();
	}
	
	private Vertices3 createCube() {
		// TODO Auto-generated method stub
		float[] vertices = {-0.5f,-0.5f,0.5f,0,1,0,0,1,
						    0.5f,-0.5f,0.5f,1,1,0,0,1,
						    0.5f,0.5f,0.5f,1,0,0,0,1,
						    -0.5f,0.5f,0.5f,0,0,0,0,1,
						    
						    0.5f,-0.5f,0.5f,0,1,1,0,0,
						    0.5f,-0.5f,-0.5f,1,1,1,0,0,
						    0.5f,0.5f,-0.5f,1,0,1,0,0,
						    0.5f,0.5f,0.5f,0,0,1,0,0,
						    
						    0.5f,-0.5f,-0.5f,0,1,0,0,-1,
						    -0.5f,-0.5f,-0.5f,1,1,0,0,-1,
						    -0.5f,0.5f,-0.5f,1,0,0,0,-1,
						    0.5f,0.5f,-0.5f,0,0,0,0,-1,
						    
						    -0.5f,-0.5f,-0.5f,0,1,-1,0,0,
						    -0.5f,-0.5f,0.5f,1,1,-1,0,0,
						    -0.5f,0.5f,0.5f,1,0,-1,0,0,
						    -0.5f,0.5f,-0.5f,0,0,-1,0,0,
						    
						    -0.5f,0.5f,0.5f,0,1,0,1,0,
						    0.5f,0.5f,0.5f,1,1,0,1,0,
						    0.5f,0.5f,-0.5f,1,0,0,1,0,
						    -0.5f,0.5f,-0.5f,0,0,0,1,0,
						    
						    -0.5f,-0.5f,0.5f,0,1,0,-1,0,
						    0.5f,-0.5f,0.5f,1,1,0,-1,0,
						    0.5f,-0.5f,-0.5f,1,0,0,-1,0,
						    -0.5f,-0.5f,-0.5f,0,0,0,-1,0};
		
		short[] indices = {0,1,3,1,2,3,
						   4,5,7,5,6,7,
						   8,9,11,9,10,11,
						   12,13,15,13,14,15,
						   16,17,19,17,18,19,
						   20,21,23,21,22,23};
		Vertices3 cube = new Vertices3(glGraphics, 24,36,false,true,true);
		cube.setVertices(vertices, 0, vertices.length);
		cube.setIndices(indices, 0, indices.length);
		
		return cube;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		angle +=deltaTime*20;
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		GLU.gluPerspective(gl, 67, glGraphics.getWidth()/(float)glGraphics.getHeight(), 0.1f, 10);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 0, 1, 3, 0, 0, 0, 0, 1, 0);
		
		
		gl.glEnable(GL10.GL_LIGHTING);
		
		ami.enable(gl);
		poi.enable(gl, GL10.GL_LIGHT0);
		dir.enable(gl, GL10.GL_LIGHT1);
		mat.enable(gl);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		
		gl.glRotatef(angle, 0, 1, 0);
		cube.bind();
		cube.draw(GL10.GL_TRIANGLES, 0, 36);
		cube.unbind();
		
		poi.disable(gl);
		dir.disable(gl);
		
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
		texture.reload();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
