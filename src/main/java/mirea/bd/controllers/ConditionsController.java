package mirea.bd.controllers;

import mirea.bd.models.Condition;
import mirea.bd.services.ConditionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/conditions")
public class ConditionsController {
    private final ConditionsService conditionsService;

    public ConditionsController(ConditionsService conditionsService) {
        this.conditionsService = conditionsService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer conditionsPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByEndDate) {
        if (page == null || conditionsPerPage == null)
            model.addAttribute("conditions", conditionsService.findAll(sortByEndDate));
        else
            model.addAttribute("conditions", conditionsService.findWithPagination(page, conditionsPerPage, sortByEndDate));
        return "conditions/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("condition", conditionsService.findOne(id));
        return "conditions/show";
    }

    @GetMapping("/new")
    public String newCondition(@ModelAttribute("condition")Condition condition) {
        return "conditions/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("condition") @Valid Condition condition,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "conditions/new";

        conditionsService.save(condition);
        return "redirect:/conditions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("condition", conditionsService.findOne(id));
        return "conditions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("condition") @Valid Condition condition, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "conditions/edit";

        conditionsService.update(id, condition);
        return "redirect:/conditions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        conditionsService.delete(id);
        return "redirect:/conditions";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "conditions/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("conditions", conditionsService.searchByDescription(query));
        return "conditions/search";
    }
}
