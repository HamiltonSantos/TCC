package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;

@Transactional
@Controller
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;
	
	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;
	
	Long idOcorrencia = 1l;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("ocorrencias", faixaValorDao.listOcorrencias());

		return "/ocorrencia/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(FaixaValor ocorrencia) {

		// Peso min e Max
		ocorrencia.setValorMin(ocorrencia.getPeso());
		ocorrencia.setValorMax(ocorrencia.getPeso());

		// Variavel
		ocorrencia.setVariavel(variavelDao.get(idOcorrencia));
		// Insere banco
		faixaValorDao.salvar(ocorrencia);

		return "redirect:/ocorrencia";
	}

	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public String nova(Model model) {

		model.addAttribute("ocorrencia", new FaixaValor());
		return "/ocorrencia/form";
	}

}
