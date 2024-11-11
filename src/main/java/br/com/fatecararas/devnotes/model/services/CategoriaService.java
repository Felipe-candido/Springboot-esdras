package br.com.fatecararas.devnotes.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecararas.devnotes.controllers.dtos.CategoriaDTO;
import br.com.fatecararas.devnotes.model.entities.Categoria;
import br.com.fatecararas.devnotes.model.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public List<CategoriaDTO> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(c -> CategoriaDTO
                        .builder()
                        .id(c.getId())
                        .descricao(c.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Categoria buscarPorId(Long id) throws Exception{
        Optional<Categoria> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new Exception("Categoria não localizada. ID: " + id);
        }

        return optional.get();
    }

    public void atualizar(Categoria categoria) throws Exception {
        Categoria c = buscarPorId(categoria.getId());
        c.setDescricao(categoria.getDescricao());
        salvar(c);
    }
    
}
