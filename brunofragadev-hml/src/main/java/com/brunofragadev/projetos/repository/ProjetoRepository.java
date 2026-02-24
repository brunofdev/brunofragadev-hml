package com.brunofragadev.projetos.repository;

import com.brunofragadev.projetos.entitys.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}