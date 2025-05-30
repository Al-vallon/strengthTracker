package strengthtracker.strengthTracker.workout;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
  @Autowired private WorkoutRepository workoutRepository;

  public List<Workout> getAllWorkouts() {
    return workoutRepository.findAll();
  }

  public Optional<Workout> getWorkoutById(Long id) {
    return workoutRepository.findById(id);
  }

  public Workout createWorkout(Workout workout) {
    return workoutRepository.save(workout);
  }

  public Workout updateWorkout(Long id, Workout workoutDetails) {
    return workoutRepository
        .findById(id)
        .map(
            workout -> {
              workout.setName(workoutDetails.getName());
              workout.setDescription(workoutDetails.getDescription());
              // Ajoutez d'autres champs à mettre à jour
              return workoutRepository.save(workout);
            })
        .orElse(null);
  }

  public void deleteWorkout(Long id) {
    workoutRepository.deleteById(id);
  }
}
