package pl.javastart.bootcamp.domain.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.bootcamp.domain.user.User;
import pl.javastart.bootcamp.domain.user.UserService;
import pl.javastart.bootcamp.domain.user.role.Role;
import pl.javastart.bootcamp.domain.user.role.UserRole;
import pl.javastart.bootcamp.domain.user.role.UserRoleService;

import java.util.Collections;

@Controller
public class AdminUserController {

    private UserService userService;
    private UserRoleService userRoleService;

    public AdminUserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/admin/uzytkownicy")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @PostMapping("/admin/uzytkownicy/{id}/ustawRoleAdmina")
    public String setRole(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = userService.findByIdOrThrow(id);

        if (!userService.isUserAdmin(user)) {
            UserRole userRole = new UserRole(user, Role.ROLE_ADMIN);
            userRoleService.save(userRole);

            user.getRoles().add(userRole);
            userService.update(user);
            redirectAttributes.addFlashAttribute("message", "Rola została zaktualizowana");
        } else {
            redirectAttributes.addFlashAttribute("warning", "Użytkownik już posiada rolę admina!");
        }
        return "redirect:/admin/uzytkownicy";
    }

    @GetMapping("/admin/uzytkownicy/{id}")
    public String user(Model model, @PathVariable Long id) {
        User user = userService.findByIdOrThrow(id);
        model.addAttribute("user", user);
        return "admin/user";
    }


}
