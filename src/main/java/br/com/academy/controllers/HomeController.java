package br.com.academy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;

@Controller
public class HomeController {	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(); //Inicializado o MV e instanciando como "mv"
		mv.setViewName("home/index"); //Setando minha view (HTML)
		mv.addObject("aluno", new Aluno());
		//mv.addObject("msg", "Mensagem vinda diretamente do controller");
		return mv;
	}
	
}
