package mirea.bd.controllers;

import mirea.bd.models.Provider;
import mirea.bd.services.ProvidersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/providers")
public class ProvidersController {
    private final ProvidersService providersService;

    public ProvidersController(ProvidersService providersService) {
        this.providersService = providersService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer providersPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortBySurname) {
        if (page == null || providersPerPage == null)
            model.addAttribute("providers", providersService.findAll(sortBySurname));
        else
            model.addAttribute("providers", providersService.findWithPagination(page, providersPerPage, sortBySurname));
        return "providers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("provider", providersService.findOne(id));
        return "providers/show";
    }

    @GetMapping("/new")
    public String newProvider(@ModelAttribute("provider") Provider provider) {
        return "providers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("provider") @Valid Provider provider,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "providers/new";

        providersService.save(provider);
        return "redirect:/providers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("provider", providersService.findOne(id));
        return "providers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("provider") @Valid Provider provider, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "providers/edit";

        providersService.update(id, provider);
        return "redirect:/providers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        providersService.delete(id);
        return "redirect:/providers";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "providers/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("providers", providersService.searchByName(query));
        return "providers/search";
    }
}
