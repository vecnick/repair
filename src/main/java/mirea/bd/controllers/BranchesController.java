package mirea.bd.controllers;

import mirea.bd.models.Branch;
import mirea.bd.services.BranchesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/branches")
public class BranchesController {
    private final BranchesService branchesService;

    public BranchesController(BranchesService branchesService) {
        this.branchesService = branchesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer branchesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByRating) {
        if (page == null || branchesPerPage == null)
            model.addAttribute("branches", branchesService.findAll(sortByRating));
        else
            model.addAttribute("branches", branchesService.findWithPagination(page, branchesPerPage, sortByRating));
        return "branches/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("branch", branchesService.findOne(id));
        return "branches/show";
    }

    @GetMapping("/new")
    public String newBranch(@ModelAttribute("branch")Branch branch) {
        return "branches/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("branch") @Valid Branch branch,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "branches/new";

        branchesService.save(branch);
        return "redirect:/branches";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("branch", branchesService.findOne(id));
        return "branches/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("branch") @Valid Branch branch, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "branches/edit";

        branchesService.update(id, branch);
        return "redirect:/branches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        branchesService.delete(id);
        return "redirect:/branches";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "branches/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("branches", branchesService.searchByAddress(query));
        return "branches/search";
    }
}
