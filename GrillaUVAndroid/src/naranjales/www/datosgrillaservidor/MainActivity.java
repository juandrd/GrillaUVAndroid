package naranjales.www.datosgrillaservidor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
	
	/*	private class backgroundService extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... arg0) {
			String fecha="";
			
			try
			{
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse response =httpClient.execute(new HttpGet("http://54.243.57.97/services/hora.php"));
				StatusLine statusLine = response.getStatusLine();
				if(statusLine.getStatusCode() == HttpStatus.SC_OK)
				{
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					fecha = out.toString();
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
				fecha = "¡Error! "+e.getMessage();
			}
			return fecha;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			TextView tv=  (TextView)findViewById(R.id.tvhora);
			tv.setText(result);
		}
		
		@Override
		protected void onPreExecute()
		{
			TextView tv=  (TextView)findViewById(R.id.tvhora);
			tv.setText("...");
		}
		
		@Override
		protected void onProgressUpdate(Void... values)
		{
			TextView tv=  (TextView)findViewById(R.id.tvhora);
			tv.setText("...");
		}
	}
	*/

}
