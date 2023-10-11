package pl.javastart.bootcamp.domain.signup.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SignupLogItemRepository extends JpaRepository<SignupLogItem, Long> {
    void deleteBySignupId(Long signupUserId);
}
