package br.com.joaopedrovicentin.crudspringtopicos.estado;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoRepository extends JpaRepository<EstadoModel, UUID> {
    
}
