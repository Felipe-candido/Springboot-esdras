package br.com.fatecararas.devnotes.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecararas.devnotes.controllers.dtos.CategoriaDTO;
import br.com.fatecararas.devnotes.model.entities.Categoria;
import br.com.fatecararas.devnotes.model.services.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasResource {
    @Autowired
    private CategoriaService service;
    
    @GetMapping
    public List<CategoriaDTO> findAll() {
        return service.buscarTodas();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/salvar")
    public Categoria salvar(@RequestBody CategoriaDTO dto) {
        var c = new Categoria();
        c.setDescricao(dto.getDescricao());
        return service.salvar(c);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id) {
        service.excluir(id);
    }

    // TODO: Cria o método de alteração de categoria.
    @PutMapping("/alterar/{id}")
    public Categoria alterar(@PathVariable("id") Long id, @RequestBody CategoriaDTO dto) {
        try {
            var categoria = service.buscarPorId(id);
            categoria.setDescricao(dto.getDescricao());
            return service.salvar(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar a categoria: " + e.getMessage());
        }
    }

    //TODO: Cria o método de busca por ID de categoria.
    @GetMapping("/{id}")
    public CategoriaDTO buscarPorId(@PathVariable("id") Long id) {
        try {
            var categoria = service.buscarPorId(id);
            return new CategoriaDTO(id, categoria.getDescricao());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a categoria: " + e.getMessage());
        }
    }
}
