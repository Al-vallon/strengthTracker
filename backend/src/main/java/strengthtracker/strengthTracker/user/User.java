package strengthtracker.strengthTracker.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Le nom d'utilisateur est obligatoire")
  @Size(min = 3, max = 50)
  @Column(unique = true, nullable = false)
  private String username;

  @NotBlank(message = "Le mot de passe est obligatoire")
  @Size(min = 8, max = 100, message = "Le mot de passe doit contenir au moins 8 caractères")
  @Column(nullable = false)
  private String password;

  @NotBlank(message = "L'email est obligatoire")
  @Email(message = "Email invalide")
  @Column(unique = true, nullable = false)
  private String email;

  @NotBlank(message = "Le prénom est obligatoire")
  private String firstName;

  @NotBlank(message = "Le nom est obligatoire")
  private String lastName;

  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  @PrePersist
  protected void onCreate() {
    this.createdDate = LocalDateTime.now();
    this.modifiedDate = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.modifiedDate = LocalDateTime.now();
  }
}
