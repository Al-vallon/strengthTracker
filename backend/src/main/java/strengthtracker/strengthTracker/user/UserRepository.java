package strengthtracker.strengthTracker.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // Custom queries if needed
  Optional<User> findByEmail(String email);
}
