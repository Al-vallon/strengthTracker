package strengthtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strengthtracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Additional methods for user-specific queries can be added here

}
