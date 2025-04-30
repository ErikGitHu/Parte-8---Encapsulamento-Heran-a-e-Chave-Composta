package br.com.mycompany.loja.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private DadosPessoais dados_pessoais;
	
	public Cliente() {
	}

	public Cliente(String nome, String cpf) {
		this.dados_pessoais = new DadosPessoais(nome, cpf);
	}

	public String getNome() {
		return this.dados_pessoais.getNome();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
