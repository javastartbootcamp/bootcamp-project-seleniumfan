package pl.javastart.bootcamp.domain.signup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javastart.bootcamp.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    List<Signup> findByUser(User user);

    List<Signup> findByUserAndCanSeeContentIsTrue(User user);

    List<Signup> findByStatus(SignupStatus status);

    Optional<Signup> findByUserId(Long id);
}
