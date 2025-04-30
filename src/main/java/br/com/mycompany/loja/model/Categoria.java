package br.com.mycompany.loja.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@EmbeddedId
	private CategoriaId categoriaid;
	
	public Categoria() {
	}

	public Categoria(String nome) {
		this.categoriaid = new CategoriaId(nome);
	}

	public String getNome() {
		return this.categoriaid.getNome();
	}

}
