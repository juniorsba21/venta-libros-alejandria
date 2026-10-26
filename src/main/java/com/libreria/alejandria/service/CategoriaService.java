package com.libreria.alejandria.service;
import com.libreria.alejandria.model.Categoria;
import com.libreria.alejandria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) { this.categoriaRepository = categoriaRepository; }
    public List<Categoria> listarCategorias() { return categoriaRepository.findAll(); }
    public Categoria buscarPorId(Long id) { return categoriaRepository.findById(id).orElse(null); }
    public void guardar(Categoria categoria) { categoriaRepository.save(categoria); }
    public void eliminar(Long id) { categoriaRepository.deleteById(id); }
}