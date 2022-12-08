package mirea.bd.controllers;

import mirea.bd.models.Branch;
import mirea.bd.models.Employee;
import mirea.bd.services.BranchesService;
import mirea.bd.services.EmployeesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer employeesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortBySurname) {
        if (page == null || employeesPerPage == null)
            model.addAttribute("employees", employeesService.findAll(sortBySurname));
        else
            model.addAttribute("employees", employeesService.findWithPagination(page, employeesPerPage, sortBySurname));
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeesService.findOne(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee")Employee employee) {
        return "employees/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "employees/new";

        employeesService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeesService.findOne(id));
        return "employees/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "employees/edit";

        employeesService.update(id, employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeesService.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "employees/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("employees", employeesService.searchByName(query));
        return "employees/search";
    }
}
