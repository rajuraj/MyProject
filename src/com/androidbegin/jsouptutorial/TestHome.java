package com.androidbegin.jsouptutorial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestHome extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView textView=(TextView)findViewById(R.id.desctxt);
		Button btn=(Button)findViewById(R.id.descbutton);

		final ProgressDialog mProgressDialog;
		mProgressDialog = new ProgressDialog(TestHome.this);
		mProgressDialog.setMessage("Fetching Notifications");
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);


		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mProgressDialog.show();

				DownloadData asyncTask = new DownloadData(new DownloadListener() {
					public void processFinish(String output) {

						if(output!=null)
					    	mProgressDialog.dismiss();

						textView.setText(output);
						textView.setClickable(true);
						Linkify.addLinks(textView, Linkify.ALL);

					}
				});
				asyncTask.execute();
			}
		});
	}




}