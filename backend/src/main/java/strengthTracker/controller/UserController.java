package strengthtracker.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import strengthtracker.entity.User;
import strengthtracker.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserRepository userRepository;

  @GetMapping
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      user.setUsername(userDetails.getUsername());
      user.setPassword(userDetails.getPassword());
      user.setEmail(userDetails.getEmail());
      user.setFirstName(userDetails.getFirstName());
      user.setLastName(userDetails.getLastName());
      user.setRole(userDetails.getRole());
      user.setActive(userDetails.isActive());
      return userRepository.save(user);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}
