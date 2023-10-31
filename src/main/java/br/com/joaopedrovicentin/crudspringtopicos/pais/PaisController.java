package br.com.joaopedrovicentin.crudspringtopicos.pais;

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
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private IPaisRepository paisRepository;

    @PostMapping("/")
    public PaisModel create(@RequestBody PaisModel paisModel){
        var pais = this.paisRepository.save(paisModel);

        return pais;
    }

    @GetMapping("/")
    public List<PaisModel> list(HttpServletRequest request){
        var paises = this.paisRepository.findAll();
        return paises;
    }

    @PutMapping("/{id}")
    public PaisModel update(@RequestBody PaisModel paisModel, @PathVariable UUID id){

        var pais = this.paisRepository.findById(id).orElse(null);

        Utils.copyNonNullProperties(paisModel, pais);

        return  this.paisRepository.save(pais);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
        Optional<PaisModel> paisOptional = this.paisRepository.findById(id);

        if (paisOptional.isPresent()) {
            PaisModel pais = paisOptional.get();

            this.paisRepository.delete(pais);

            return ResponseEntity.ok("País excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao excluir o país: " + e.getMessage());
        }
    }
}
