package strengthtracker.strengthTracker.workout;

import jakarta.persistence.*;
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

  private String name;
  private String description;
  // Ajoutez d'autres champs pertinents
}
