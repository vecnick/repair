package mirea.bd.controllers;

import mirea.bd.models.RepairMaterial;
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

    public RepairMaterialsController(RepairMaterialsService repairMaterialsService) {
        this.repairMaterialsService = repairMaterialsService;
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
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("repairMaterial", repairMaterialsService.findOne(id));
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
