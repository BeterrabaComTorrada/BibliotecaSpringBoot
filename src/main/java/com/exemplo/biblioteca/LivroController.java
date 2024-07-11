package com.exemplo.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    @PostMapping
    public Livro adicionar(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        Livro livroExistente = livroRepository.findById(id).orElse(null);
        if (livroExistente != null) {
            livroExistente.setTitulo(livro.getTitulo());
            livroExistente.setAutor(livro.getAutor());
            return livroRepository.save(livroExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livroRepository.deleteById(id);
    }
}
