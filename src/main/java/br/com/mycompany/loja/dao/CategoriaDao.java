package br.com.mycompany.loja.dao;

import javax.persistence.EntityManager;

import br.com.mycompany.loja.model.Categoria;
import br.com.mycompany.loja.model.CategoriaId;

public class CategoriaDao {
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

}
