package br.com.mycompany.loja.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable{
	
	private static final long serialVersionUID = 1l;
	private String nome;
	
	public CategoriaId() {
	}

	public CategoriaId(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
