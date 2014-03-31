package com.poocode.movies;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.poocode.movies.get.set.CategoriasGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;


public class er extends Activity {
	private ProgressDialog pDialog;
	protected JSONArray mCategoria  = null;
	protected GridView categorias;
	ArrayList<CategoriasGet> mArraylist = new ArrayList<CategoriasGet>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list_movies);
		
		categorias = (GridView) findViewById(R.id.gridCategorias);
		
		new LoadAllDatos().execute();
		
	}
	
	class LoadAllDatos extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			pDialog = new ProgressDialog(er.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		protected String doInBackground(String... args) {
			
			ArrayList<NameValuePair> postValores = new ArrayList<NameValuePair>();
			postValores.add(new BasicNameValuePair("estado", "1"));
			
			String response = null;
			try {
				response = CustomHttpClient.executeHttpPost("http://www.poocode.com/serviciowebmovie/categorias.php", postValores);
				JSONObject jsonObject = new JSONObject(response);
				mCategoria = jsonObject.getJSONArray("categorias");
				
				for (int i = 0; i < mCategoria.length(); i++) {
					JSONObject categoriaObj = mCategoria.getJSONObject(i);
					CategoriasGet mMapeo = new CategoriasGet(categoriaObj.getInt("id"), categoriaObj.getString("nombre"));
					//mMapeo.setData(categoriaObj.getString("photo"));
					mArraylist.add(mMapeo);
				}
				
			} catch (Exception e) {
				Log.e("WebService", e.getMessage());
			}
			
			return null;
		}
		
		protected void onPostExecute(String file_url) {
			
			//AdapterCategorias grid = new AdapterCategorias(er.this, mArraylist);
			//categorias.setAdapter(grid);
			
			//String[] datos = new String[] {"Android", "iOS","Windows", "Blackberry" };
			
			categorias.setAdapter(new AdapterCategorias(er.this, mArraylist));
			
			Toast toast2 = Toast.makeText(er.this, mArraylist+" salgo ", Toast.LENGTH_SHORT);
			toast2.show();
			
			pDialog.dismiss();
		}
	}

}
