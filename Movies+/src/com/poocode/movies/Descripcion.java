package com.poocode.movies;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import com.poocode.movies.get.set.PeliculasGet;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class Descripcion extends Activity {

	private Bundle bolsa;
	private ProgressDialog pDialog;
	private String id;
	protected JSONArray mPelicula = null;
	ArrayList<PeliculasGet> publicAvaiable = new ArrayList<PeliculasGet>();
	protected ListView mLisView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_movies);
		bolsa = getIntent().getExtras();
		id = String.valueOf(bolsa.getInt("id"));
		mLisView = (ListView) findViewById(R.id.listView);
		new LoadAllDatos().execute();
	}

	class LoadAllDatos extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Descripcion.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			
			ArrayList<NameValuePair> postValores = new ArrayList<NameValuePair>();
			postValores.add(new BasicNameValuePair("id",id));
			
			String response = null;
			try {
				response = CustomHttpClient.executeHttpPost("http://poocode.com/serviciowebmovie/peliculasConsulta.php", postValores);
				JSONObject jsonObject = new JSONObject(response);
				mPelicula = jsonObject.getJSONArray("peliculas");
				
				for (int i = 0; i < mPelicula.length(); i++) {
					JSONObject peliculaObj = mPelicula.getJSONObject(i);
					PeliculasGet mMapeo = new PeliculasGet(
							peliculaObj.getInt("id"), peliculaObj.getString("nombre"),
							peliculaObj.getString("ano"),
							peliculaObj.getString("duarcaion"),
							peliculaObj.getString("director"), peliculaObj.getInt("puntos"),
							peliculaObj.getString("peSinopsis"), peliculaObj.getString("peCalidad"), peliculaObj.getString("genero"));
							mMapeo.setData(peliculaObj.getString("photo"));
							publicAvaiable.add(mMapeo);
				}
				
			} catch (Exception e) {
				Log.e("WebService", e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			
			AdapterPeliculas mPelicula = new AdapterPeliculas(Descripcion.this, publicAvaiable);
			mLisView.setAdapter(mPelicula);
			
			pDialog.dismiss();
		}

	}// Fin AsyncTask

}
