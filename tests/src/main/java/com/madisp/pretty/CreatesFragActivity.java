package com.madisp.pretty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class CreatesFragActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Pretty.wrap(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emty_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.main, new TestFragment()).commit();
		}
	}
}
