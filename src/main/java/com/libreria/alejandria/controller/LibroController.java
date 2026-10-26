package com.libreria.alejandria.controller;

import com.libreria.alejandria.service.CategoriaService;
import com.libreria.alejandria.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.alejandria.model.Libro;

@Controller
public class LibroController {

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("paginaActual", "index");
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("librosDestacados", libroService.listarDestacados());
        return "index";
    }

    @GetMapping("/libros")
    public String libros(@RequestParam(required = false) Long categoria, Model model) {
        model.addAttribute("paginaActual", "libros");
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaSeleccionada", categoria);
        if (categoria == null) {
            model.addAttribute("libros", libroService.listarLibros());
        } else {
            model.addAttribute("libros", libroService.listarPorCategoria(categoria));
        }
        return "libros";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("paginaActual", "contacto");
        return "contacto";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("paginaActual", "login");
        return "login";
    }
    
    @GetMapping("/libros/{id}")
    public String detalleLibro(@PathVariable Long id, Model model) {
        // Implementación para mostrar los detalles de un libro
        Libro libro = libroService.buscarPorId(id);
        if (libro == null) {
            // Gestionar el caso en el que no se encuentra el libro
            return "redirect:/libros";
        }
        model.addAttribute("paginaActual", "libros");
        model.addAttribute("libro", libro);
        return "detalle-libro";
    }
    
}