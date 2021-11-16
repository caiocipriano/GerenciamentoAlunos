package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

import java.util.List;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {
			
	  Aluno findById(long id);

	  @Query("SELECT c FROM Aluno c WHERE c.status = 'ATIVO'")
	  public List<Aluno>findByStatusAtivos();
	  
	  @Query("SELECT a FROM Aluno a WHERE a.status = 'INATIVO'")
	  public List<Aluno>findByStatusInativos();
	 
	  @Query("SELECT i FROM Aluno i WHERE i.status = 'CANCELADO'")
	  public List<Aluno>findByStatusCancelados();
	  
	  @Query("SELECT o FROM Aluno o WHERE o.status = 'TRANCADO'")
	  public List<Aluno>findByStatusTrancados();
	 
	  public List<Aluno>findByNomeContainingIgnoreCase(String nome);  ///Método do JPQL contém LIKE
	  
	 
}
	