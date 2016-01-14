package com.kevinkuai.mygl3D;

import javax.microedition.khronos.opengles.GL10;

import com.kevinkuai.GLGame.Camera2D;
import com.kevinkuai.GLGame.EulerCamera;
import com.kevinkuai.GLGame.GLScreen;
import com.kevinkuai.GLGame.PointLight;
import com.kevinkuai.GLGame.SpriteBatcher;
import com.kevinkuai.GLGame.Texture;
import com.kevinkuai.GLGame.TextureRegion;
import com.kevinkuai.GLGame.Vector;
import com.kevinkuai.GLGame.Vector3;
import com.kevinkuai.GLGame.Vertices3;
import com.kevinkuai.framework.Game;

public class EulerCamScreen extends GLScreen{
	Texture boxTex;
	Texture arrowTex;
	TextureRegion arrow;
	Vertices3 cube;
	PointLight light;
	EulerCamera euler;
	SpriteBatcher batcher;
	Camera2D guiCam;
	Vector touchPos;
	float lastX=-1;
	float lastY=-1;
	

	public EulerCamScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		boxTex = new Texture(glGame,"crate.png", true);
		cube = createCube();
		light = new PointLight();
		light.setPosition(3, 3, -3);
		euler = new EulerCamera(67, 
				glGraphics.getWidth()/(float)glGraphics.getHeight(),1,100);
		euler.getPosition().set(0,1,3);
		
		arrowTex = new Texture(glGame,"arrow.png");
		batcher = new SpriteBatcher(glGraphics,1);
		guiCam=new Camera2D(glGraphics,480,320);
		arrow = new TextureRegion(arrowTex, 0,0,64,64);
		touchPos=new Vector();
		
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
		game.getInput().getTouchEvents();
		float x = game.getInput().getTouchX(0);
		float y = game.getInput().getTouchY(0);
		guiCam.touchToWorld(touchPos.set(x, y));
		
		if(game.getInput().isTouchDown(0)){
			if(touchPos.x<64 && touchPos.y<64){
				Vector3 direction = euler.getDirection();
				euler.getPosition().add(direction.mul(deltaTime));
			}else{
				if(lastX==-1){
					lastX=x;
					lastY=y;
				}else{
					euler.rotate((x-lastX)/10, (y-lastY)/10);
					
					lastX=x;
					lastY=y;
				}
			}
				
		}else{
			lastX=-1;
			lastY=-1;
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		
		euler.setMatrices(gl);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_LIGHTING);
		
		boxTex.bind();
		cube.bind();
		light.enable(gl, GL10.GL_LIGHT0);
		
		for(int z=0; z>=-8; z-=2){
			for (int x=-4; x<=4; x += 2){
				gl.glPushMatrix();
				gl.glTranslatef(x, 0, z);
				cube.draw(GL10.GL_TRIANGLES, 0, 36);
				gl.glPopMatrix();
			}
		}
		
		cube.unbind();
		
		gl.glDisable(GL10.GL_LIGHTING);
		gl.glDisable(GL10.GL_DEPTH_TEST);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		guiCam.setViewportAndMatrices();
		batcher.beginBatch(arrowTex);
		batcher.drawSprite(32, 32, 64, 64, arrow);
		batcher.endBatch();
		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		boxTex.reload();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
