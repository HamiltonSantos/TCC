package br.com.faddvm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Fisioterapeuta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String login;
	private String senha;
	private String nome;
	@Transient
	private String contraSenha;

	public String getContraSenha() {
		return contraSenha;
	}

	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}

	@Override
	public String toString() {
		return "Fisioterapeuta [id=" + id + ", login=" + login + ", senha="
				+ senha + ", nome=" + nome + ", contraSenha=" + contraSenha
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
