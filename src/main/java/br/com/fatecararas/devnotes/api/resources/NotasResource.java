package br.com.fatecararas.devnotes.api.resources;

import org.springframework.web.bind.annotation.RestController;

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

import br.com.fatecararas.devnotes.model.entities.Nota;
import br.com.fatecararas.devnotes.model.services.NotaService;

@RestController
@RequestMapping("/api/v1/notas")
public class NotasResource {

    @Autowired
    private NotaService service;

    // LISTAR NOTAS
    @GetMapping
    public List<Nota> findAll() {
        return service.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Nota buscarPorId(@PathVariable("id") Long id) {
        try {
            return service.buscarPorId(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a nota: " + e.getMessage());
        }
    }

    // SALVAR NOTA
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/salvar")
    public Nota salvar(@RequestBody Nota nota) {
        return service.salvar(nota);
    }

    // ATUALIZAR NOTA
    @PutMapping("/alterar/{id}")
    public Nota atualizar(@PathVariable("id") Long id, @RequestBody Nota nota) {
        try {
            Nota notaExistente = service.buscarPorId(id);
            notaExistente.setCategoria(nota.getCategoria());
            notaExistente.setConteudo(nota.getConteudo());
            return service.salvar(notaExistente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a nota: " + e.getMessage());
        }
    }

    // EXCLUIR NOTA
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id) {
        try {
            service.excluir(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir a nota: " + e.getMessage());
        }
    }
}