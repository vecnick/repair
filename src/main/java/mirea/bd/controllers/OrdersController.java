package mirea.bd.controllers;

import mirea.bd.models.Order;
import mirea.bd.services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer ordersPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByTotalPrice) {
        if (page == null || ordersPerPage == null)
            model.addAttribute("orders", ordersService.findAll(sortByTotalPrice));
        else
            model.addAttribute("orders", ordersService.findWithPagination(page, ordersPerPage, sortByTotalPrice));
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", ordersService.findOne(id));
        return "orders/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order")Order order) {
        return "orders/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") @Valid Order order,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "orders/new";

        ordersService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", ordersService.findOne(id));
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "orders/edit";

        ordersService.update(id, order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "orders/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("orders", ordersService.searchByName(query));
        return "orders/search";
    }
}
