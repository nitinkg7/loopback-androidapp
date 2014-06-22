package com.nitin.loopbackandroid;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;

public class UserDetailsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details, menu);
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
		private ListView listview;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_user_details,
					container, false);
			listview=(ListView)rootView.findViewById(R.id.list);
			sendRequest();
			return rootView;
		}
			private void sendRequest() {
	        LoopAndroidApplication app = new LoopAndroidApplication(); 
	        RestAdapter adapter = app.getLoopBackAdapter();

	        UserDetailsRepository repository = adapter.createRepository(UserDetailsRepository.class);

	        repository.findAll(new UserDetailsRepository.FindAllCallback<UserDetailModel>() {
	            @Override
	            public void onSuccess(List<UserDetailModel> models) {
	                listview.setAdapter(new UserListAdapter(getActivity(), models));
	            }

	            @Override
	            public void onError(Throwable t) {
	                Log.e(getTag(), "Could not fetch data from server! Some error Occured!", t);
	                showResult("Cound not fetch data from server!");
	            }
	        });
	    }

	    private void showResult(String message) {
	        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	    }
	    private static class UserListAdapter extends ArrayAdapter<UserDetailModel> {
	        public UserListAdapter(Context context, List<UserDetailModel> list) {
	            super(context, 0, list);
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            if (convertView == null) {
	                convertView = LayoutInflater.from(getContext()).inflate(
	                        R.layout.list_item, null);
	            }

	            UserDetailModel model = getItem(position);
	            if (model == null) return convertView;

	            TextView textView1 = (TextView)convertView.findViewById(R.id.username);
	            textView1.setText(model.getUsername());
	            TextView textView2 = (TextView)convertView.findViewById(R.id.email);
	            textView2.setText(model.getEmail()+"  ");
	            TextView textView3 = (TextView)convertView.findViewById(R.id.contact);
	            textView3.setText(String.valueOf(model.getContact()));
	            
	            return convertView;
	        }
	    }
	}

}
