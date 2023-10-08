package pl.javastart.bootcamp.domain.user.role;

import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
