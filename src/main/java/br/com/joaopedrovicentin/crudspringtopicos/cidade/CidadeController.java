package br.com.joaopedrovicentin.crudspringtopicos.cidade;

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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private ICidadeRepository cidadeRepository;

    @PostMapping("/")
    public CidadeModel create(@RequestBody CidadeModel cidadeModel){
        var cidade = this.cidadeRepository.save(cidadeModel);

        return cidade;
    }

    @GetMapping("/")
    public List<CidadeModel> list(HttpServletRequest request){
        var cidades = this.cidadeRepository.findAll();
        return cidades;
    }

    @PutMapping("/{id}")
    public CidadeModel update(@RequestBody CidadeModel cidadeModel, @PathVariable UUID id){

        var cidade = this.cidadeRepository.findById(id).orElse(null);

        Utils.copyNonNullProperties(cidadeModel, cidade);

        return  this.cidadeRepository.save(cidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
        Optional<CidadeModel> cidadeOptional = this.cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            CidadeModel cidade = cidadeOptional.get();

            this.cidadeRepository.delete(cidade);

            return ResponseEntity.ok("Cidade excluída com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao excluir a cidade: " + e.getMessage());
        }
    }
}
