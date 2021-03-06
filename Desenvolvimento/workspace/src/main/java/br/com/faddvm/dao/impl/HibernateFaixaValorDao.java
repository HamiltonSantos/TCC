package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;

@Repository
public class HibernateFaixaValorDao implements FaixaValorDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public FaixaValor salvar(FaixaValor faixaValor) {
		manager.persist(faixaValor);
		return faixaValor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listOcorrencias() {
		List<FaixaValor> ocorrencias = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 1l).getResultList();
		return ocorrencias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listaIntercorrencias() {
		List<FaixaValor> intercorrencias = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 2l).getResultList();
		return intercorrencias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listaIndices() {
		List<FaixaValor> indices = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 3l).getResultList();
		return indices;
	}

	@Override
	public void remover(FaixaValor faixaValor) {

		Query query = manager.createQuery(
				"delete from FaixaValor where id = ?1").setParameter(1,
				faixaValor.getId());

		query.executeUpdate();
	}

	@Override
	public FaixaValor get(Long id) {
		return manager.find(FaixaValor.class, id);
	}

	@Override
	public FaixaValor getByDescricaoAndVariavel(String descricao,
			Long idVariavel) {
		FaixaValor faixaValor = null;
		Query query = manager
				.createQuery(
						"From FaixaValor f where f.descricao = ?1"
								+ " And f.variavel.id = ?2")
				.setParameter(1, descricao).setParameter(2, idVariavel);

		try {
			faixaValor = (FaixaValor) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return faixaValor;
	}

	@Override
	public FaixaValor getByValor(Long valor, Long variavelId) {
		FaixaValor faixaValor = null;
		String queryString = "SELECT f.* FROM faddvm.FaixaValor f "
				+ "where f.variavel_id = ?1 "
				+ "and ?2 between f.valorMin and f.valorMax";
		Query query = manager.createNativeQuery(queryString, FaixaValor.class)
				.setParameter(1, variavelId).setParameter(2, valor);

		try {
			faixaValor = (FaixaValor) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return faixaValor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getHistoricoByFaixa(FaixaValor faixaValor) {
		List<Historico> historico = null;
		Query query = manager.createQuery(
				"From Historico as h where h.faixa.id = ?1").setParameter(1,
				faixaValor.getId());

		historico = query.getResultList();
		return historico;
	}

	@SuppressWarnings("unchecked")
	public List<FaixaValor> getFaixasVariavelByName(FaixaValor faixa) {
		List<FaixaValor> faixas = null;

		Query query = manager
				.createQuery(
						"From FaixaValor f where f.descricao = ?1 and f.variavel.id = ?2")
				.setParameter(1, faixa.getDescricao())
				.setParameter(2, faixa.getVariavel().getId());

		faixas = query.getResultList();

		return faixas;
	}
}
