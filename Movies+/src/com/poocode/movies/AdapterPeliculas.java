package com.poocode.movies;

import java.util.ArrayList;

import com.poocode.movies.get.set.CategoriasGet;
import com.poocode.movies.get.set.PeliculasGet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPeliculas extends BaseAdapter{
	
	protected Activity activity;
	protected ArrayList<PeliculasGet> items;
	
	public AdapterPeliculas(Activity activity, ArrayList<PeliculasGet> items) {
        this.activity = activity;
        this.items = items;
    }

	@Override
	public int getCount() {
		return items.size();
	}
	
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return items.get(position).getIdP();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		
        if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.list_item_layout2, null);
		
		}
        
        PeliculasGet peliculas = items.get(position);
        
        ImageView mImageView = (ImageView) vi.findViewById(R.id.imgesPrimary);
        mImageView.setImageBitmap(peliculas.getPhoto());
        
        //Nombre 
        
        TextView nombre = (TextView) vi.findViewById(R.id.teNombre);
        TextView genero = (TextView) vi.findViewById(R.id.teGenero);
        TextView director = (TextView) vi.findViewById(R.id.texDirector);
        nombre.setText(peliculas.getDescripcion());
        genero.setText(peliculas.getGenero());
        director.setText(peliculas.getDirector());
        
		return vi;
	}	

}
