package com.kevinkuai.mygl3D;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainList extends ListActivity{
	
	String Classes[] = {"Vertices3DTest", "LightScreenTest", "EulerLightTest"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String> (MainList.this, android.R.layout.simple_list_item_1, Classes));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try {
			Class OurClass = Class.forName("com.kevinkuai.mygl3D."+Classes[position]);
			Intent OurIntent = new Intent(MainList.this, OurClass);
			startActivity(OurIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
