package strengthtracker.strengthTracker.user;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import strengthtracker.strengthTracker.config.JwtUtil;
import strengthtracker.strengthTracker.user.dto.LoginRequestDto;

// import strengthtracker.strengthTracker.user.mapper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;

  // @Autowired private UserMapper userMapper;

  @GetMapping
  public List<User> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id).orElse(null);
  }

  @PostMapping
  public User createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
    return userService.updateUser(id, userDetails);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
    User user = userService.findByEmail(loginRequest.getEmail());
    if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      String token = jwtUtil.generateToken(user.getEmail());
      return ResponseEntity.ok(Map.of("token", token));
    }
    return ResponseEntity.status(401).body("Email ou mot de passe invalide");
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody User user) {
    if (userService.findByEmail(user.getEmail()) != null) {
      return ResponseEntity.badRequest().body("Email déjà utilisé");
    }

    User created = userService.createUser(user);
    return ResponseEntity.ok(created);
  }
}
