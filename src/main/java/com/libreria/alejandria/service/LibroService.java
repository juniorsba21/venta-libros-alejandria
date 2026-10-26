package com.libreria.alejandria.service;
import com.libreria.alejandria.model.Libro;
import com.libreria.alejandria.repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    public LibroService(LibroRepository libroRepository) { this.libroRepository = libroRepository; }
    public List<Libro> listarLibros() { return libroRepository.findAll(); }
    public List<Libro> listarDestacados() { return libroRepository.findTop3ByOrderByIdAsc(); }
    public Libro buscarPorId(Long id) { return libroRepository.findById(id).orElse(null); }
    public void guardar(Libro libro) { libroRepository.save(libro); }
    public void eliminar(Long id) { libroRepository.deleteById(id); }
    public List<Libro> listarPorCategoria(Long categoriaId) {return libroRepository.findByCategoriaId(categoriaId);}
}