package pl.javastart.bootcamp.domain.faq;

import org.springframework.stereotype.Service;
import pl.javastart.bootcamp.config.notfound.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class FaqService {
    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public Faq insert(Faq faq) {
        if (Objects.nonNull(faq.getId())) {
            throw new IllegalArgumentException("ID should be null when adding");
        }
        return faqRepository.save(faq);
    }

    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID should be not be null when deleting");
        }
        faqRepository.deleteById(id);
    }

    public List<Faq> findAll() {
        return faqRepository.findAll();
    }

    public Faq findById(Long id) {
        return faqRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Faq update(Faq faq) {
        if (Objects.isNull(faq.getId())) {
            throw new IllegalArgumentException("ID should be not be null when updating");
        }
        return faqRepository.save(faq);
    }
}
