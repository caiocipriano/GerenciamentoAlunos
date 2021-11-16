package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;


@Controller
public class AlunoController {
		
	
	@Autowired
	private AlunoDao alunoRepositorio;	//Anotação JPA
	
	
	@GetMapping("/inserir")
	public ModelAndView InsertAlunos(Aluno aluno) {	//Metodo vinculado ao Objeto aluno 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/formAluno");		//Assim que inserir, vai retornar a html formAluno
		mv.addObject("aluno",new Aluno());		//Vincula ao Objeto Aluno	
		return mv;
	}
	
	
	@GetMapping("/filtroAlunos")
	public ModelAndView listagem(){
		ModelAndView mv = new ModelAndView();			//Move para VIEW que contém o filtro de alunos
		mv.setViewName("Aluno/filtroAlunos");
		
		return mv;
		
	}
	
	@PostMapping("/IstAlunos")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br ) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Aluno/formAluno"); //Mantém o usuário na página de formulario
			mv.addObject(aluno);
		}else {
		mv.setViewName("redirect:/alunos-adicionados");
		alunoRepositorio.save(aluno);	// O método inserirAluno vai inserir no banco atráves do JPA
		}                        
		return mv;	
	}
	
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos(){
		ModelAndView mv = new ModelAndView();			//Método que vai retorna a View onde contém a lista de alunos
		mv.setViewName("Aluno/listAlunos");
		mv.addObject("alunosList", alunoRepositorio.findAll());
		return mv;
		
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) { //Path indentica o ID
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alterar");						 //Altera os dados apontando para o ID
		Aluno aluno = alunoRepositorio.getOne(id);	        	
		mv.addObject("aluno",aluno);
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Aluno aluno) { 
		ModelAndView mv = new ModelAndView();		
		alunoRepositorio.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados"); // alterar() vai redirecionar para página de cadastro
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) { //Método para excluir, ele percorre pelo ID no banco
		alunoRepositorio.deleteById(id);
		return "redirect:/alunos-adicionados";
	}
	
	@GetMapping("/encontrar/{id}")
	public ModelAndView encontraAluno(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/aluno-encontrado");
		mv.addObject("alunosEncontrados", alunoRepositorio.findById(id));        	
		return mv;
	}
	
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView(); //Retorna pagina com alunos ativos	
		mv.setViewName("Aluno/filtroAlunos");
		return mv;
	}
	
	
	//View que contém query filtrando por Status
	
	@GetMapping("alunos-ativos")
	public ModelAndView listagemAtivos(){
		ModelAndView mv = new ModelAndView();									
		mv.setViewName("Aluno/alunos-ativos");
		mv.addObject("alunosAtivos", alunoRepositorio.findByStatusAtivos());
		return mv;
		
	}
	
	@GetMapping("alunos-inativos")
	public ModelAndView listagemInativos(){
		ModelAndView mv = new ModelAndView();			
		mv.setViewName("Aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunoRepositorio.findByStatusInativos());
		return mv;
		
	}
	
	@GetMapping("alunos-cancelados")
	public ModelAndView listagemCancelados(){
		ModelAndView mv = new ModelAndView();			
		mv.setViewName("Aluno/alunos-cancelados");
		mv.addObject("alunosCancelados", alunoRepositorio.findByStatusCancelados());
		return mv;
		
	}
	
	
	
	@GetMapping("alunos-trancados")
	public ModelAndView listagemTrancados(){
		ModelAndView mv = new ModelAndView();			
		mv.setViewName("Aluno/alunos-trancados");
		mv.addObject("alunosTrancados", alunoRepositorio.findByStatusTrancados());
		return mv;
		
	}
	
	
	//--------------------------------------------------------------------
	
	
	@GetMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required=false)String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno>listaAlunos;
		if(nome == null  || nome.trim().isEmpty()) {
			listaAlunos = alunoRepositorio.findAll();
		}else {
			listaAlunos = alunoRepositorio.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("ListaDeAlunos", listaAlunos);
		mv.setViewName("Aluno/pesquisa-resultado");
		return mv;
	}
	

}
