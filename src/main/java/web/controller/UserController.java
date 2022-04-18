package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String allUser(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users/users";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("users") User user) {
        return "/users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/new";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", userService.getUserId(id));
        return "/users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user
            , BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/users/edit";
        } else {
            userService.editUser(id, user);
            return "redirect:/";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
