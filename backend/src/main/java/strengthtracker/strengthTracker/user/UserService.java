package strengthtracker.strengthTracker.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(Long id, User userDetails) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              user.setUsername(userDetails.getUsername());
              if (!user.getPassword().equals(userDetails.getPassword())) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
              }
              user.setEmail(userDetails.getEmail());
              user.setFirstName(userDetails.getFirstName());
              user.setLastName(userDetails.getLastName());
              return userRepository.save(user);
            })
        .orElse(null);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }
}