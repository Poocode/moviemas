package com.poocode.movies;


import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import com.poocode.movies.get.set.CategoriasGet;
import com.viewpagerindicator.CirclePageIndicator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.BufferType;


public class Movies extends Activity implements android.widget.AdapterView.OnItemClickListener{

	private ViewPager viewPager;
	private RelativeLayout page1;
	private LinearLayout page2;
	private TextView mTitle;
	protected JSONArray categoriasWeb = null;
	private ProgressDialog pDialog;
	protected JSONArray mCategoria  = null;
	protected GridView categorias;
	ArrayList<CategoriasGet> mArraylist = new ArrayList<CategoriasGet>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MainPageAdapter());
		CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		// TabPageIndicator titleIndicator = (TabPageIndicator)findViewById(R.id.indicator);
		
		titleIndicator.setViewPager(viewPager);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movies, menu);
		return true;
	}

	class MainPageAdapter extends PagerAdapter {

		@Override
		public CharSequence getPageTitle(int position) {
			String title = null;

			switch (position) {
			case 0:
				title = "uno";
				break;
			default:
				title = "dos";
				break;
			}

			return title;
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			View page = null;
			switch (position) {
			case 0:
				if (page1 == null) {
					page1 = (RelativeLayout) LayoutInflater.from(Movies.this)
							.inflate(R.layout.view_pager_bienvenida, null);
					initListView();
				}
				page = page1;
				break;
			default:
				if (page2 == null) {
					page2 = (LinearLayout) LayoutInflater.from(Movies.this).inflate(R.layout.view_pager_login, null);
					new LoadAllDatos().execute();
				}
				page = page2;
				break;
			}

			((ViewPager) collection).addView(page, 0);

			return page;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((View) view);
		}

		private void initListView() {

			mTitle = (TextView) page1.findViewById(R.id.texMtitle);
			mTitle.setText(Html.fromHtml("<h1>Movies + Peliculas</h1>"),BufferType.SPANNABLE);
			/*
			 * String[] items = new String[50]; for (int i = 0; i < 50; i++){
			 * items[i] = "Item " + i; } page3.setAdapter(new
			 * ArrayAdapter<String>(MainActivity.this, R.layout.textview,
			 * items)); page3.setOnItemClickListener(new OnItemClickListener() {
			 * 
			 * @Override public void onItemClick(AdapterView<?> parent, View
			 * view, int position, long id){ Toast.makeText(MainActivity.this,
			 * (String) parent.getItemAtPosition(position),
			 * Toast.LENGTH_SHORT).show(); } });
			 */
		}
	
		class LoadAllDatos extends AsyncTask<String, String, String> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				
				pDialog = new ProgressDialog(Movies.this);
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
				
				categorias = (GridView) page2.findViewById(R.id.gridCategorias);
				categorias.setOnItemClickListener(Movies.this);
				categorias.setAdapter(new AdapterCategorias(Movies.this, mArraylist));				
				pDialog.dismiss();
				
				
			}
		}//Fin asy
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		CategoriasGet vlr = (CategoriasGet) parent.getItemAtPosition(position);
		
		//Toast toast2 = Toast.makeText(this, "esta es la po"+vlr.getId(), Toast.LENGTH_SHORT);
		//toast2.show();
		
		Intent intent = new Intent("com.poocode.movies.DESCRIPCION");
		Bundle bolsa = new Bundle();
		bolsa.putInt("id", vlr.getId());
		intent.putExtras(bolsa);
		startActivity(intent);
	}

	
}
