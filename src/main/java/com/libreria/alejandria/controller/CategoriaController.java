package com.libreria.alejandria.controller;
import com.libreria.alejandria.model.Categoria;
import com.libreria.alejandria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "form-categoria";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return "form-categoria";
        }
        categoriaService.guardar(categoria);
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "form-categoria";
        }
        return "redirect:/admin";
    }

    
@PostMapping("/eliminar/{id}")
public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        categoriaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada.");
    } catch (DataIntegrityViolationException e) {
        redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene libros asociados.");
    }
    return "redirect:/admin";
}
}