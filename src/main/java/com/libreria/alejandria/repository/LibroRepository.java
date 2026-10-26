package com.libreria.alejandria.repository;
import com.libreria.alejandria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findTop3ByOrderByIdAsc();
    List<Libro> findByCategoriaId(Long categoriaId);
}
