package com.libreria.alejandria.repository;
import com.libreria.alejandria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}