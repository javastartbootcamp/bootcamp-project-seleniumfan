package pl.javastart.bootcamp.domain.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.bootcamp.domain.user.User;
import pl.javastart.bootcamp.domain.user.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminUserController {
    private UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/uzytkownicy")
    public String users(Model model) {
        List<User> users = userService.findAll();
        Map<Long, Boolean> isAdminMap = users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        userService::isUserAdmin
                ));

        model.addAttribute("users", userService.findAll());
        model.addAttribute("isAdminMap", isAdminMap);
        return "admin/users";
    }

    @PostMapping("/admin/uzytkownicy/{id}/ustawRoleAdmina")
    public String setRole(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = userService.findByIdOrThrow(id);

        if (!userService.isUserAdmin(user)) {
            userService.assignAdminRole(id);
            redirectAttributes.addFlashAttribute("message", "Rola została zaktualizowana");
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
