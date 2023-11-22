package springsecurity.springbootsecurity.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.RoleService;
import springsecurity.springbootsecurity.services.UserService;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@Valid
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleServices;
    private final UserService userServices;
    private static final String REDIRECT = "redirect:/admin";


    public AdminController(RoleService roleServices, UserService userServices) {
        this.roleServices = roleServices;
        this.userServices = userServices;
    }

    @GetMapping(value = "")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServices.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.getUserById(id));
        return "user";
    }

    @GetMapping(value = "/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleServices.getAllRoles());
        return "create";
    }

    @PostMapping(value = "/new")
    public String add(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {
        // проверка на ошибки
        if (bindingResult.hasErrors()) {

            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);

            return "create";
        }

        if ( ids == null || ids.isEmpty()) {
            bindingResult.rejectValue("roles", "error.roles.empty", "No roles selected");
            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "create";
        }

        try {

            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            userServices.addUser(user);
            return REDIRECT;
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username", "duplicate", "This is username is already taken");
            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "create";
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServices.removeUser(id);
        return REDIRECT;
    }

    @GetMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.getUserById(id));
        model.addAttribute("allRoles", roleServices.getAllRoles());
        return "edit";
    }

    @PatchMapping(value = "/edit")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);

            return "edit";
        }

        if (ids == null || ids.isEmpty()) {
            bindingResult.rejectValue("roles", "errors","No roles selected");
            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "edit";
        }

        try {
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            userServices.updateUser(user);
            return REDIRECT;
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username", "duplicate", "This is username is already taken");
            model.addAttribute("allRoles", roleServices.getAllRoles());
            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "edit";
        }
    }

}