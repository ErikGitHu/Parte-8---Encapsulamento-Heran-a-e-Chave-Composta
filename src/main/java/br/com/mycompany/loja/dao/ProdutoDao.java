package br.com.mycompany.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.mycompany.loja.model.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto consultarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public BigDecimal consultarPorPreco(String nome) {
		String jpql = "SELECT p.preço FROM Produto p WHERE p.nome =: nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> consultarTudo(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();	
	}
	
	public List<Produto> consultarPorParametros(String nome, String descriçao, BigDecimal preço, LocalDate data){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filter = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			filter = builder.and(filter, builder.equal(from.get("nome"), nome));
		}
		if(descriçao != null && !descriçao.trim().isEmpty()) {
			filter = builder.and(filter, builder.equal(from.get("descriçao"), descriçao));
		}
		if(preço != null) {
			filter = builder.and(filter, builder.equal(from.get("preço"), preço));
		}
		if(data != null) {
			filter = builder.and(filter, builder.equal(from.get("data"), data));
		}
		query.where(filter);
		return em.createQuery(query).getResultList();
		
	}
}
