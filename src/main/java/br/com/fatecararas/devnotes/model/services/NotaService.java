package br.com.fatecararas.devnotes.model.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecararas.devnotes.model.entities.Nota;
import java.util.Optional;
import br.com.fatecararas.devnotes.model.repositories.
NotaRepository;

@Service
public class NotaService {
    
    @Autowired
    private NotaRepository repository;

    public List<Nota> findAll() {
        return repository.findAll();
    }

    public Nota salvar(Nota nota) {
        return repository.save(nota);
    }

    
    public Nota buscarPorId(Long id) throws Exception {
        Optional<Nota> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new Exception("Nota não localizada. ID: " + id);
        }
        return optional.get();
    }


    public void atualizar(Nota nota) throws Exception {
        Nota n = buscarPorId(nota.getId());
        n.setCategoria(nota.getCategoria());
        n.setConteudo(nota.getConteudo());
        salvar(n);
    }

    public void excluir(Long id) throws Exception {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
