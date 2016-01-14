package com.kevinkuai.mygl3D;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.kevinkuai.GLGame.GLGame;
import com.kevinkuai.GLGame.GLScreen;
import com.kevinkuai.GLGame.Vertices3;
import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Screen;

public class Vertices3DTest extends GLGame{

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		
		return new HierScreen(this);
	}
	
	class V3DScreen extends GLScreen{

		Vertices3 vertices3;
		
		public V3DScreen(Game game) {
			super(game);
			// TODO Auto-generated constructor stub
			vertices3 = new Vertices3(glGraphics, 6,0,true,false, false);
			vertices3.setVertices(new float[]{-0.5f,-0.5f,-3,1,0,0,0.5f,
											  0.5f,-0.5f,-3,1,0,0,0.5f,
											  0.0f,0.5f,-3,1,0,0,0.5f,
											  
											  0.0f,-0.5f,-5,0,1,0,1,
											  1.0f,-0.5f,-5,0,1,0,1,
											  0.5f,0.5f,-5,0,1,0,1}
					 ,0, 42);
		}

		@Override
		public void update(float deltaTime) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void present(float deltaTime) {
			// TODO Auto-generated method stub
			GL10 gl=glGraphics.getGL();
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			
			GLU.gluPerspective(gl, 67, 
					glGraphics.getWidth()/(float)glGraphics.getHeight(), 0.1f, 10);
			
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			
			vertices3.bind();
			vertices3.draw(GL10.GL_TRIANGLES, 3, 3);
			vertices3.draw(GL10.GL_TRIANGLES, 0, 3);
			vertices3.unbind();
			
			gl.glDisable(GL10.GL_BLEND);
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

}
