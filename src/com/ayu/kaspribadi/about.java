package com.ayu.kaspribadi;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;

public class about extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Tentang");
		actionBar.setDisplayHomeAsUpEnabled(false);
	}
}
