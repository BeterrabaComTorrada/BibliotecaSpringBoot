package com.exemplo.biblioteca;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Carregar os dados do arquivo JSON
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Livro>> typeReference = new TypeReference<List<Livro>>() {};
        InputStream inputStream = new ClassPathResource("livros.json").getInputStream();
        List<Livro> livros = mapper.readValue(inputStream, typeReference);
        
        // Salvar os dados no banco de dados
        livroRepository.saveAll(livros);
        System.out.println("Livros salvos no banco de dados.");
    }
}
