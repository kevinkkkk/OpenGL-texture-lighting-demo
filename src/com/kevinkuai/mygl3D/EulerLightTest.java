package com.kevinkuai.mygl3D;

import com.kevinkuai.GLGame.GLGame;
import com.kevinkuai.framework.Screen;

public class EulerLightTest extends GLGame{

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new EulerCamScreen(this);
	}
	

}
