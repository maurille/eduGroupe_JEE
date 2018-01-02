package com.mau.nouvelAn.metier;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	private int id;
															private String titre;
															private String corps;
	
	public Message() {};
	public Message(int id, String titre, String corps) {
																super();
																this.id = id;
																this.titre = titre;
																this.corps = corps;
															}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		this.corps = corps;
	}
	
	
	

}
