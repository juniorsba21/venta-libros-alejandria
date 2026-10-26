package com.libreria.alejandria.controller;
import com.libreria.alejandria.service.CategoriaService;
import com.libreria.alejandria.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public AdminController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/admin", "/administracion"})
    public String administracion(Model model) {
        model.addAttribute("paginaActual", "administracion");
        model.addAttribute("libros", libroService.listarLibros());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "admin";
    }
}
