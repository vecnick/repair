package mirea.bd.controllers;

import mirea.bd.models.Service;
import mirea.bd.services.ServicesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/services")
public class ServicesController {
    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer servicesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByServicePrice) {
        if (page == null || servicesPerPage == null)
            model.addAttribute("services", servicesService.findAll(sortByServicePrice));
        else
            model.addAttribute("services", servicesService.findWithPagination(page, servicesPerPage, sortByServicePrice));
        return "services/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("service", servicesService.findOne(id));
        return "services/show";
    }

    @GetMapping("/new")
    public String newService(@ModelAttribute("service")Service service) {
        return "services/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("service") @Valid Service service,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "services/new";

        servicesService.save(service);
        return "redirect:/services";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("service", servicesService.findOne(id));
        return "services/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("service") @Valid Service service, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "services/edit";

        servicesService.update(id, service);
        return "redirect:/services";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        servicesService.delete(id);
        return "redirect:/services";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "services/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("servises", servicesService.searchByName(query));
        return "services/search";
    }
}
