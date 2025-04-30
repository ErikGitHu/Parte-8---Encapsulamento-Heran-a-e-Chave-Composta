package br.com.mycompany.loja.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mycompany.loja.dao.CategoriaDao;
import br.com.mycompany.loja.dao.ClienteDao;
import br.com.mycompany.loja.dao.PedidoDao;
import br.com.mycompany.loja.dao.ProdutoDao;
import br.com.mycompany.loja.model.Categoria;
import br.com.mycompany.loja.model.CategoriaId;
import br.com.mycompany.loja.model.Cliente;
import br.com.mycompany.loja.model.ItemPedido;
import br.com.mycompany.loja.model.Pedido;
import br.com.mycompany.loja.model.Produto;
import br.com.mycompany.loja.util.JPAUtil;
import br.com.mycompany.loja.vo.RelatorioDeVendasVO;

public class Usuario {

	public static void main(String[] args) {
		licao_pratica_1_e_2();
		
		licao_pratica_3();
		
		licao_pratica_4();
		
		licao_pratica_5();
		
		licao_pratica_6();
		
		licao_pratica_7();
		
	}

	private static void licao_pratica_7() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		List<Produto> produto = produtoDao.consultarPorParametros("Notebook", null, null, null);
	}

	private static void licao_pratica_6() {
		EntityManager em = JPAUtil.getEntityManager();
		Pedido p = new Pedido();
		PedidoDao pedidoDao = new PedidoDao(em);
	
		//pedidoDao.consultarPorIdComCliente(1l);
		
		em.close();
		//System.out.println(p.getCliente().getNome());
	}

	private static void licao_pratica_5() {
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		
		BigDecimal somaDoTotalPedidos = pedidoDao.consultaPorAgregaçao();
		System.out.println(somaDoTotalPedidos);
		
		em.getTransaction().begin();
		
		List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioDePedidos();
		//relatorio.forEach(System.out::println);
	}

	private static void licao_pratica_4() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		PedidoDao pedidoDao = new PedidoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		
		em.getTransaction().begin();
		Produto p = produtoDao.consultarPorId(1l);
		
		Cliente cliente = new Cliente("Henrique", "12345678900");
		Pedido pedido = new Pedido(cliente);
		pedido.AdicionarItem(new ItemPedido(1, pedido, p));
		
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		
		System.out.println(pedido.getValorTotal());
		
		em.getTransaction().commit();
		em.close();
	}

	private static void licao_pratica_3() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.consultarPorId(1l);
		//System.out.println(p.getNome());
		
		BigDecimal listarPorPreco = produtoDao.consultarPorPreco("Notebook");
		//System.out.println(listarPorPreco);
		
		List<Produto> listarTudo = produtoDao.consultarTudo();
		//listarTudo.forEach(t -> System.out.println(t));
	}

	private static void licao_pratica_1_e_2() {
		Categoria categoria = new Categoria("Básico");
		Produto produto = new Produto("Notebook", "Design leve e compacto", new BigDecimal(3449.90), categoria);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		em.persist(produto);
		produto.setNome("Notebook");
		em.flush();
		em.clear();
		
		produto = em.merge(produto);
		produto.setNome("Notebook");
		em.flush();
	
		em.remove(produto);
		em.clear();
	}
	
}
