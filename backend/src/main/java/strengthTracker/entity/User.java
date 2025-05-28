package strengthtracker.entity;

import jakarta.persistence.*;
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

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String email;

  private String firstName;
  private String lastName;
  private String role;

  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;
  private boolean isActive;

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
