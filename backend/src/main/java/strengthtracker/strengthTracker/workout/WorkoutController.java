package strengthtracker.strengthTracker.workout;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
  @Autowired private WorkoutService workoutService;

  @GetMapping
  public List<Workout> getWorkouts() {
    return workoutService.getAllWorkouts();
  }

  @GetMapping("/{id}")
  public Workout getWorkoutById(@PathVariable Long id) {
    return workoutService.getWorkoutById(id).orElse(null);
  }

  @PostMapping
  public Workout createWorkout(@RequestBody Workout workout) {
    return workoutService.createWorkout(workout);
  }

  @PutMapping("/{id}")
  public Workout updateWorkout(@PathVariable Long id, @RequestBody Workout workoutDetails) {
    return workoutService.updateWorkout(id, workoutDetails);
  }

  @DeleteMapping("/{id}")
  public void deleteWorkout(@PathVariable Long id) {
    workoutService.deleteWorkout(id);
  }
}
