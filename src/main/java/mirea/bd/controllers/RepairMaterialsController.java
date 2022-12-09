package mirea.bd.controllers;

import mirea.bd.models.Provider;
import mirea.bd.models.RepairMaterial;
import mirea.bd.services.ProvidersService;
import mirea.bd.services.RepairMaterialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/repairMaterials")
public class RepairMaterialsController {
    private final RepairMaterialsService repairMaterialsService;
    private final ProvidersService providersService;

    public RepairMaterialsController(RepairMaterialsService repairMaterialsService, ProvidersService providersService) {
        this.repairMaterialsService = repairMaterialsService;
        this.providersService = providersService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer repairMaterialsPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByRetailPrice) {
        if (page == null || repairMaterialsPerPage == null)
            model.addAttribute("repairMaterials", repairMaterialsService.findAll(sortByRetailPrice));
        else
            model.addAttribute("repairMaterials", repairMaterialsService.findWithPagination(page, repairMaterialsPerPage, sortByRetailPrice));
        return "repairMaterials/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("provider") Provider provider) {
        model.addAttribute("repairMaterial", repairMaterialsService.findOne(id));
        model.addAttribute("services", repairMaterialsService.getServicesByRepairMaterialId(id));
        Provider owner = repairMaterialsService.getRepairMaterialOwner(id);

        if (owner != null)
            model.addAttribute("owner", owner);
        else
            model.addAttribute("providers", providersService.findAll());

        return "repairMaterials/show";
    }

    @GetMapping("/new")
    public String newRepairMaterial(@ModelAttribute("repairMaterial")RepairMaterial repairMaterial) {
        return "repairMaterials/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("repairMaterial") @Valid RepairMaterial repairMaterial,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "repairMaterials/new";

        repairMaterialsService.save(repairMaterial);
        return "redirect:/repairMaterials";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("repairMaterial", repairMaterialsService.findOne(id));
        return "repairMaterials/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("repairMaterial") @Valid RepairMaterial repairMaterial, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "repairMaterials/edit";

        repairMaterialsService.update(id, repairMaterial);
        return "redirect:/repairMaterials";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        repairMaterialsService.delete(id);
        return "redirect:/repairMaterials";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        repairMaterialsService.release(id);
        return "redirect:/repairMaterials/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("provider") Provider selectedProvider) {
        repairMaterialsService.assign(id, selectedProvider);
        return "redirect:/repairMaterials/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "repairMaterials/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("repairMaterials", repairMaterialsService.searchByName(query));
        return "repairMaterials/search";
    }
}
