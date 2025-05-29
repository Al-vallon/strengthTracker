package strengthtracker.repository;

import strengthtracker.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Additional methods for user-specific queries can be added here

}
