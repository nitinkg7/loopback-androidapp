package com.nitin.loopbackandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;

public class MainActivity extends ActionBarActivity {
	RestAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		TextView username, email, contact;
		EditText userText, emailtext, contactText;
		Button createNew, existing;
		RestAdapter adapter;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			username=(TextView) rootView.findViewById(R.id.textView1);
			email=(TextView) rootView.findViewById(R.id.textView2);
			contact=(TextView) rootView.findViewById(R.id.textView3);
			userText=(EditText) rootView.findViewById(R.id.editText1);
			emailtext=(EditText) rootView.findViewById(R.id.editText2);
			contactText=(EditText) rootView.findViewById(R.id.editText3);
			createNew=(Button)rootView.findViewById(R.id.button1);
			existing=(Button)rootView.findViewById(R.id.button2);
			createNew.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				if(userText.getText().toString().trim().equals("") || emailtext.getText().toString().trim().equals("") 
						|| contactText.getText().toString().trim().equals(""))
				{
					Toast.makeText(getActivity(), "Please enter all the fields!", Toast.LENGTH_LONG).show();
				}
				else{
					// send data to server here.
				sendRequest();
				}
				}
			});
			existing.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(getActivity(), UserDetailsActivity.class);
					startActivity(intent);
				}
			});
			return rootView;
		}
			    /**
	     * Our custom ModelRepository subclass. See Lesson One for more information.
	     */
	    private void sendRequest() {
			LoopAndroidApplication app = new LoopAndroidApplication();
			RestAdapter adapter = app.getLoopBackAdapter();

			UserDetailsRepository repository = adapter.createRepository(UserDetailsRepository.class);

			UserDetailModel model = repository.createModel(ImmutableMap.of("username","nitin","email", "a@a.com","contact", "1562832"));
			
			model.setUsername(userText.getText().toString());
			model.setEmail(emailtext.getText().toString());
			model.setContact(Integer.valueOf(contactText.getText().toString()));

			model.save(new Model.Callback() {

				@Override
				public void onSuccess() {
					showResult("New User Created!");
					Intent intent=new Intent();
					intent.setClass(getActivity(), UserDetailsActivity.class);
					startActivity(intent);
				}

				@Override
				public void onError(Throwable t) {
					Log.e(getTag(), "Could not create new user!", t);
					showResult("Could not create new user!");
				}
			});
		}

		void showResult(String message) {
			Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
		}

	}
}
