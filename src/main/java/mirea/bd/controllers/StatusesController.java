package mirea.bd.controllers;

import mirea.bd.models.Status;
import mirea.bd.services.StatusesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/statuses")
public class StatusesController {
    private final StatusesService statusesService;

    public StatusesController(StatusesService statusesService) {
        this.statusesService = statusesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer statusesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByName) {
        if (page == null || statusesPerPage == null)
            model.addAttribute("statuses", statusesService.findAll(sortByName));
        else
            model.addAttribute("statuses", statusesService.findWithPagination(page, statusesPerPage, sortByName));
        return "statuses/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("status", statusesService.findOne(id));
        return "statuses/show";
    }

    @GetMapping("/new")
    public String newStatus(@ModelAttribute("status") Status status) {
        return "statuses/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("status") @Valid Status status,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "statuses/new";

        statusesService.save(status);
        return "redirect:/statuses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("status", statusesService.findOne(id));
        return "statuses/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("status") @Valid Status status, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "statuses/edit";

        statusesService.update(id, status);
        return "redirect:/statuses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        statusesService.delete(id);
        return "redirect:/statuses";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "statuses/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("statuses", statusesService.searchByName(query));
        return "statuses/search";
    }
}
