package br.com.joaopedrovicentin.crudspringtopicos.estado;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "estados")
public class EstadoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
