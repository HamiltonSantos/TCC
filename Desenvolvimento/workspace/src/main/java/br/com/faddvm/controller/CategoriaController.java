package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.util.validator.CategoriaValidator;

@Transactional
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao categoriaDao;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("categorias", categoriaDao.lista());
		return "/categoria/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Categoria categoria, BindingResult result) {

		ValidationUtils.invokeValidator(new CategoriaValidator(), categoria,
				result);

		if (result.hasErrors()) {
			return "/categoria/form";
		}
		categoriaDao.salva(categoria);

		return "redirect:/categoria/" + categoria.getId();
	}

	@RequestMapping("/nova")
	public String nova(Model model) {

		Categoria categoria = new Categoria();
		categoria.setStatus('C');

		model.addAttribute("categoria", categoria);

		return "/categoria/form";
	}

	@RequestMapping(value = "/{categoriaId}", method = RequestMethod.GET)
	public String mostra(@PathVariable String categoriaId, Model model) {

		Categoria categoria = categoriaDao.get(new Long(categoriaId));
		model.addAttribute("categoria", categoria);

		return "/categoria/mostra";
	}
}
