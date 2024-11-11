package br.com.fatecararas.devnotes.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecararas.devnotes.model.entities.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{
    
}
