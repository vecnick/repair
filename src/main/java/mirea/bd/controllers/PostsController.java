package mirea.bd.controllers;

import mirea.bd.models.Post;
import mirea.bd.services.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostsController {
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer postsPerPage) {
        if (page == null || postsPerPage == null)
            model.addAttribute("posts", postsService.findAll());
        else
            model.addAttribute("posts", postsService.findWithPagination(page, postsPerPage));
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postsService.findOne(id));
        return "posts/show";
    }

    @GetMapping("/new")
    public String newPost(@ModelAttribute("post")Post post) {
        return "posts/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("post") @Valid Post post,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "posts/new";

        postsService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("post", postsService.findOne(id));
        return "posts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "posts/edit";

        postsService.update(id, post);
        return "redirect:/posts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        postsService.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "posts/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("posts", postsService.searchByName(query));
        return "posts/search";
    }
}
