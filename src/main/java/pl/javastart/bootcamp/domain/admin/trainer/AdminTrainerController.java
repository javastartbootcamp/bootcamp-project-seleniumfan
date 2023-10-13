package pl.javastart.bootcamp.domain.admin.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminTrainerController {
    private final AdminTrainerService adminTrainerService;

    public AdminTrainerController(AdminTrainerService adminTrainerService) {
        this.adminTrainerService = adminTrainerService;
    }

    @GetMapping("/admin/trenerzy")
    public String trainerList(Model model) {
        List<Trainer> trainers = adminTrainerService.findAll();
        model.addAttribute("trainers", trainers);
        return "admin/trainer/trainerList";
    }

    @GetMapping("/admin/trenerzy/{id}")
    public String viewTrainer(@PathVariable Long id, Model model) {
        Trainer trainer = adminTrainerService.findByIdOrThrow(id);
        model.addAttribute("trainer", trainer);
        return "admin/trainer/trainer";
    }

    @PostMapping("/admin/trenerzy/{id}/edytuj")
    public String changeTrainerData(@PathVariable Long id, Model model) {
        Trainer trainer = adminTrainerService.findByIdOrThrow(id);
        model.addAttribute("trainer", trainer);
        model.addAttribute("mode", "edit");
        return "admin/trainer/trainerAddOrEdit";
    }

    @PostMapping("/admin/trenerzy/{id}/usun")
    public String deleteTrainerData(@PathVariable Long id) {
        adminTrainerService.deleteById(id);
        return "redirect:/admin/trenerzy";
    }

    @PostMapping("/admin/trenerzy/edytuj")
    public String editTrainer(Trainer trainer) {
        adminTrainerService.save(trainer);
        return "redirect:/admin/trenerzy/" + trainer.getId();
    }

    @PostMapping("/admin/trenerzy/dodaj")
    public String addTrainer(Trainer trainer) {
        adminTrainerService.save(trainer);
        return "redirect:/admin/trenerzy/" + trainer.getId();
    }

    @GetMapping("/admin/trenerzy/dodaj")
    public String addTrainerFor(Model model) {
        model.addAttribute("trainer", new Trainer());
        model.addAttribute("mode", "add");
        return "admin/trainer/trainerAddOrEdit";
    }
}
