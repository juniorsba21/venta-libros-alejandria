package com.libreria.alejandria.controller;
import com.libreria.alejandria.model.Libro;
import com.libreria.alejandria.service.CategoriaService;
import com.libreria.alejandria.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/libros")
public class LibroAdminController {
    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroAdminController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "form-libro";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("libro") Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "form-libro";
        }
        libroService.guardar(libro);
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Libro libro = libroService.buscarPorId(id);
        if (libro != null) {
            model.addAttribute("libro", libro);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "form-libro";
        }
        return "redirect:/admin";
    }

@PostMapping("/eliminar/{id}")
public String eliminar(@PathVariable Long id) {
    libroService.eliminar(id);
    return "redirect:/admin";
}
}