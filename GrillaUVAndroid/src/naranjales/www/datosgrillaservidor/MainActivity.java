package naranjales.www.datosgrillaservidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.os.AsyncTask;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
		private class backgroundService extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... arg0) {
			String informacion="";
			
			try
			{
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse response =httpClient.execute(new HttpGet("http://cedesoft.univalle.edu.co/tareaAndroid/"));
				StatusLine statusLine = response.getStatusLine();
				if(statusLine.getStatusCode() == HttpStatus.SC_OK)
				{
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					informacion = out.toString();
					
				}
				else
				{
					//Close the connection
					response.getEntity().getContent().close();
					throw new IOException(statusLine.getReasonPhrase());
				}
			}
			catch(Exception e)
			{
				informacion = "¡Error! "+e.getMessage();
			}
			return informacion;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			TextView tv=  (TextView)findViewById(R.id.textView1);
			tv.setText(result);
		}
		
		@Override
		protected void onPreExecute()
		{
			TextView tv=  (TextView)findViewById(R.id.textView1);
			tv.setText("...");
		}
		
		@Override
		protected void onProgressUpdate(Void... values)
		{
			TextView tv=  (TextView)findViewById(R.id.textView1);
    		tv.setText("...");
		}
	}


}
