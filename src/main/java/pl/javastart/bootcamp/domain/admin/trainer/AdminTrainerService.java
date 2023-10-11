package pl.javastart.bootcamp.domain.admin.trainer;

import org.springframework.stereotype.Service;
import pl.javastart.bootcamp.config.notfound.ResourceNotFoundException;

import java.util.List;

@Service
public class AdminTrainerService {
    private final AdminTrainerRepository adminTrainerRepository;

    public AdminTrainerService(AdminTrainerRepository adminTrainerRepository) {
        this.adminTrainerRepository = adminTrainerRepository;
    }

    public List<Trainer> findAll() {
        return adminTrainerRepository.findAll();
    }

    public Trainer findByIdOrThrow(Long id) {
        return adminTrainerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void save(Trainer trainer) {
        adminTrainerRepository.save(trainer);
    }

    public void deleteById(Long id) {
        adminTrainerRepository.deleteById(id);
    }
}
