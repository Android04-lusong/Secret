package com.song.Secret;

import com.song.Secret.atys.AtyLogin;
import com.song.Secret.atys.AtyTimeline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String token=Config.getCachedToken(this);
		startActivity(new Intent(this,AtyTimeline.class));
//		if (token!=null) {
//			//token不为空跳转到AtyTimeline
//			Intent i=new Intent(this,AtyTimeline.class);
//			i.putExtra(Config.KEY_TOKEN, token);
//			startActivity(i);
//		}else {
//			//token为空则跳转到AtyLogin
//			startActivity(new Intent(this,AtyLogin.class));
//		}
//		finish();
	}

	
}
