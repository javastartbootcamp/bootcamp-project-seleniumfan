package pl.javastart.bootcamp.domain.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.bootcamp.domain.admin.trainer.AdminTrainerService;
import pl.javastart.bootcamp.domain.admin.trainer.Trainer;
import pl.javastart.bootcamp.domain.faq.FaqService;
import pl.javastart.bootcamp.domain.opinion.OpinionService;
import pl.javastart.bootcamp.domain.training.Training;
import pl.javastart.bootcamp.domain.training.TrainingCategory;
import pl.javastart.bootcamp.domain.training.TrainingFirstDateComparator;
import pl.javastart.bootcamp.domain.training.TrainingService;

import java.util.List;

@Controller
public class HomeController {

    private TrainingService trainingService;
    private OpinionService opinionService;

    private AdminTrainerService adminTrainerService;
    private FaqService faqService;
    public HomeController(TrainingService trainingService, OpinionService opinionService, AdminTrainerService adminTrainerService, FaqService faqService) {
        this.trainingService = trainingService;
        this.opinionService = opinionService;
        this.adminTrainerService = adminTrainerService;
        this.faqService = faqService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Training> trainings = trainingService.findPlannedByCategory(TrainingCategory.DEVELOPER);
        trainings.sort(new TrainingFirstDateComparator());
        List<Trainer> trainers = adminTrainerService.findAll();
        model.addAttribute("trainers", trainers);
        model.addAttribute("trainings", trainings);
        model.addAttribute("opinions", opinionService.findAll());
        model.addAttribute("faq", faqService.findAll());
        return "home";
    }
}
