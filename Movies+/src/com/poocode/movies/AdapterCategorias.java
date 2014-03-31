package com.poocode.movies;

import java.util.ArrayList;
import com.poocode.movies.get.set.CategoriasGet;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCategorias extends BaseAdapter {

	protected Activity activity;
	protected ArrayList<CategoriasGet> items;
	
	public AdapterCategorias(Activity activity, ArrayList<CategoriasGet> items) {
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
		return items.get(position).getId();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ImageView picture;
        TextView name;
        if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.list_item_layout, null);
			vi.setLayoutParams(new GridView.LayoutParams(124, 124));
			vi.setPadding(2, 2, 2, 2);
		}
        
        CategoriasGet mCategoria = items.get(position);
        ImageView mImageView = (ImageView) vi.findViewById(R.id.imgesPrimary);
     
        TextView title = (TextView) vi.findViewById(R.id.titleImages);
        title.setText(mCategoria.getNombreCategoria());
	
        if(mCategoria.getNombreCategoria().equals("ACCIÓN")){
        	mImageView.setImageResource(R.drawable.accion_poocode);
        }else if(mCategoria.getNombreCategoria().equals("AVENTURA")){
        	mImageView.setImageResource(R.drawable.aventura_poocode);
        }else if(mCategoria.getNombreCategoria().equals("DRAMA")){
        	mImageView.setImageResource(R.drawable.drama_poocode);
        }else if(mCategoria.getNombreCategoria().equals("CIENCIA FICCIÓN")) {
        	mImageView.setImageResource(R.drawable.ciencia_poocode);
        }else if(mCategoria.getNombreCategoria().equals("ROMANCE")){
        	mImageView.setImageResource(R.drawable.romance_poocode);
        }else if(mCategoria.getNombreCategoria().equals("FAMILIAR")){
        	mImageView.setImageResource(R.drawable.familiar_poocode);
        }else if(mCategoria.getNombreCategoria().equals("TERROR")){
        	mImageView.setImageResource(R.drawable.terror_poocode);
        }else if(mCategoria.getNombreCategoria().equals("SUSPENSO")){
        	mImageView.setImageResource(R.drawable.suspenso_poocode);
        }else if(mCategoria.getNombreCategoria().equals("COMEDIA")){
        	mImageView.setImageResource(R.drawable.comedia_poocode);
        }else if(mCategoria.getNombreCategoria().equals("BÉLICO GUERRA")){
        	mImageView.setImageResource(R.drawable.belico);
        }else{
        	mImageView.setImageResource(R.drawable.poocode);
        }
        
		return vi;
	}	
}
