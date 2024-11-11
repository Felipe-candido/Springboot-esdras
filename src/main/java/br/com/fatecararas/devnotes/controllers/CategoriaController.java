package br.com.fatecararas.devnotes.controllers;

import java.util.List;

import br.com.fatecararas.devnotes.model.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fatecararas.devnotes.controllers.dtos.CategoriaDTO;
import br.com.fatecararas.devnotes.model.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public String categorias() {        
        return "categorias/listar";
    }

    @GetMapping("/cadastrar")
    public String criar(Categoria categoria) {
        return "categorias/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(Categoria categoria) {
        service.salvar(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/atualizar")
    public String atualizar(Categoria categoria) {
        try {
            service.atualizar(categoria);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/categorias";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        service.excluir(id);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String preEdicao(@PathVariable("id") Long id, Model model) {
        try {
            Categoria categoria = service.buscarPorId(id);
            model.addAttribute("categoria", categoria);
            return "categorias/cadastrar";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/categorias";
    }

    // Inclusão dos objetos necessários para a listagem e cadastro de categorias
    @ModelAttribute(name = "categorias")
    public List<CategoriaDTO> getCategorias() {
        return service.buscarTodas();
    }
}
