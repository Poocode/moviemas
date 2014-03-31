package com.poocode.movies.get.set;

import org.w3c.dom.Text;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class PeliculasGet {

	private int idPelicula;
	private String descripcion;
	private String ano;
	private String duracion;
	private String director;
	private int puntuacion;
	private String sinopsis;
	private String calidad;
	private String genero;
	private Bitmap photo;
	protected String data;

	public PeliculasGet(int idPelicula, String descripcion, String ano,
			String duracion, String director, int puntuacion, String sinopsis,
			String calidad,String genero) {
		
		this.idPelicula = idPelicula;
		this.descripcion = descripcion;
		this.ano = ano;
		this.duracion = duracion;
		this.director = director;
		this.puntuacion = puntuacion;
		this.sinopsis = sinopsis;
		this.calidad = calidad;
		this.genero = genero;
	}
	
	public int getIdP(){
		return idPelicula;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public String getano(){
		return ano;
	}
	
	public String getDuracion(){
		return duracion;
	}
	
	public String getDirector(){
		return director;
	}
	
	public int getPuntuacion(){
		return puntuacion;
	}
	
	public String getSinopsis(){
		return sinopsis;
	}
	
	public String getCalidad(){
		return calidad;
	}
	
	public String getGenero(){
		return genero;
	}
	
	public void setData(String data) {
	    this.data = data;
	    try {   
	      byte[] byteData = Base64.decode(data, Base64.DEFAULT);
	      this.photo = BitmapFactory.decodeByteArray( byteData, 0, byteData.length);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	     
	  public Bitmap getPhoto() {
	    return photo;
	  }
}
