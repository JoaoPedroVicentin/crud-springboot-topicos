package br.com.joaopedrovicentin.crudspringtopicos.cidade;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "cidades")
public class CidadeModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
