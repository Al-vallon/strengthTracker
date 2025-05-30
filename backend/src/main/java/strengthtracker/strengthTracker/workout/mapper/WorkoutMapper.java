package strengthtracker.strengthTracker.workout.mapper;

import org.springframework.stereotype.Component;
import strengthtracker.strengthTracker.workout.Workout;
import strengthtracker.strengthTracker.workout.dto.WorkoutDto;

@Component
public class WorkoutMapper {
  public WorkoutDto toDto(Workout workout) {
    WorkoutDto dto = new WorkoutDto();
    dto.setId(workout.getId());
    dto.setName(workout.getName());
    dto.setDescription(workout.getDescription());
    return dto;
  }
}
