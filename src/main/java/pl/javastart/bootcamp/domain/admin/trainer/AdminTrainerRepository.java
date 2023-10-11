package pl.javastart.bootcamp.domain.admin.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTrainerRepository extends JpaRepository<Trainer, Long> {
}
