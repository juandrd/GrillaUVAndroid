package naranjales.www.datosgrillaservidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
	
	public void obtenerDatos(View v)
	{		
		new backgroundService().execute();
	}
	
		private class backgroundService extends AsyncTask<Void, Void, String>
	{

		JSONArray arrayInformacion;
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
					arrayInformacion = new JSONArray(informacion);
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
			try {
				informacion=arrayInformacion.getString(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return informacion;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			TableLayout tl= (TableLayout)findViewById(R.id.tableLayout1);
		

			
			for(int i=0;i<arrayInformacion.length();i++)
			{
				
			try {
				TableRow tr = new TableRow(MainActivity.this);

				
		        JSONObject objeto=arrayInformacion.getJSONObject(i);
				
				TextView tv_id=new TextView(MainActivity.this);
				tv_id.setText(objeto.get("id").toString());

				
				TextView tv_name=new TextView(MainActivity.this);
				tv_name.setText(objeto.get("name").toString());

				
				TextView tv_lastname=new TextView(MainActivity.this);
				tv_lastname.setText(objeto.get("lastname").toString());

				
				tr.addView(tv_id);
				tr.addView(tv_name);
				tr.addView(tv_lastname);
			
				
			    tl.addView(tr,new TableLayout.LayoutParams(
			                LayoutParams.FILL_PARENT,
			                LayoutParams.WRAP_CONTENT));
				

		

				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
			View b = findViewById(R.id.button1);
			b.setVisibility(View.INVISIBLE);
			
		}
		
		@Override
		protected void onPreExecute()
		{

		}
		
		@Override
		protected void onProgressUpdate(Void... values)
		{

		}
	}


}
