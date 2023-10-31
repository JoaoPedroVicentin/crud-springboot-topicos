package br.com.joaopedrovicentin.crudspringtopicos.estado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedrovicentin.crudspringtopicos.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private IEstadoRepository estadoRepository;

    @PostMapping("/")
    public EstadoModel create(@RequestBody EstadoModel estadoModel){
        var estado = this.estadoRepository.save(estadoModel);

        return estado;
    }

    @GetMapping("/")
    public List<EstadoModel> list(HttpServletRequest request){
        var estados = this.estadoRepository.findAll();
        return estados;
    }

    @PutMapping("/{id}")
    public EstadoModel update(@RequestBody EstadoModel estadoModel, @PathVariable UUID id){

        var estado = this.estadoRepository.findById(id).orElse(null);

        Utils.copyNonNullProperties(estadoModel, estado);

        return  this.estadoRepository.save(estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
        Optional<EstadoModel> estadoOptional = this.estadoRepository.findById(id);

        if (estadoOptional.isPresent()) {
            EstadoModel estado = estadoOptional.get();

            this.estadoRepository.delete(estado);

            return ResponseEntity.ok("Estado excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao excluir o estado: " + e.getMessage());
        }
    }
}
