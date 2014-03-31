package com.poocode.movies.get.set;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class CategoriasGet {
	protected int id;
	protected String nombre;
	protected String data;
	protected Bitmap photo;

	public CategoriasGet(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombreCategoria() {
		return nombre;
	}

	public void setData(String data) {
		this.data = data;
		try {
			byte[] byteData = Base64.decode(data, Base64.DEFAULT);
			this.photo = BitmapFactory.decodeByteArray(byteData, 0,
					byteData.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bitmap getPhoto() {
		return photo;
	}

}
