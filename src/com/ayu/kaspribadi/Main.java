package com.ayu.kaspribadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button mulai;
	private Button pengaturan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// setOnclick event button
		mulai = (Button) findViewById(R.id.mulai);
		pengaturan = (Button) findViewById(R.id.pengaturan);

		mulai.setOnClickListener(this);
		pengaturan.setOnClickListener(this);
	}

	// onclick event, start new acvity (intent)
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mulai:
			// intent start
			startActivity(new Intent(this,Mulai.class));
			break;

		case R.id.pengaturan:
			//intent about
			startActivity(new Intent(this,option.class));
			break;

		default:
			break;
		}
	}
}