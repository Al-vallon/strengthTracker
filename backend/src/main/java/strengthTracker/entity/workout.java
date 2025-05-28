package strengthtracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "workouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String type; 

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false)
  private int duration; // in minutes

  @Column(nullable = false)
  private int caloriesBurned;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @PrePersist
  protected void onCreate() {
    if (this.date == null) {
      this.date = LocalDateTime.now();
    }
  }
}
