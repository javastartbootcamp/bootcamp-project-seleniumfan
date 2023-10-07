package pl.javastart.bootcamp.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.bootcamp.config.notfound.ResourceNotFoundException;
import pl.javastart.bootcamp.domain.faq.Faq;
import pl.javastart.bootcamp.domain.faq.FaqService;
import pl.javastart.bootcamp.domain.opinion.Opinion;
import pl.javastart.bootcamp.domain.opinion.OpinionService;

import java.util.Optional;

@RequestMapping("/admin/faq")
@Controller
public class AdminFaqController {

    private FaqService faqService;

    public AdminFaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("")
    public String pages(Model model) {
        model.addAttribute("faq", faqService.findAll());
        return "admin/faq/faqs";
    }

    @GetMapping("/dodaj")
    public String add(Model model) {
        model.addAttribute("faq", new Faq());
        return "admin/faq/editFaq";
    }

    @PostMapping("/dodaj")
    public String addFaq(Faq faq) {
        faqService.insert(faq);
        return "redirect:/admin/faq";
    }

    @GetMapping("/{id}/edytuj")
    public String editPage(@PathVariable Long id, Model model) {
        Faq faq = faqService.findById(id);
        model.addAttribute("faq", faq);
        return "admin/faq/editFaq";
    }

    @GetMapping("/{id}/usun")
    public String deletePage(@PathVariable Long id, Model model) {
        faqService.delete(id);
        return "redirect:/admin/faq";
    }

    @PostMapping("/edytuj")
    public String updateFaq(Faq faq) {
        faqService.update(faq);
        return "redirect:/admin/faq";
    }
}
