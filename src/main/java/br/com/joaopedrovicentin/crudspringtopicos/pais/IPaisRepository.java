package br.com.joaopedrovicentin.crudspringtopicos.pais;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaisRepository extends JpaRepository<PaisModel, UUID> {
    
}
