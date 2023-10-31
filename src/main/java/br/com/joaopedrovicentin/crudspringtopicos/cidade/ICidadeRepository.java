package br.com.joaopedrovicentin.crudspringtopicos.cidade;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICidadeRepository extends JpaRepository<CidadeModel, UUID>{
    
}
