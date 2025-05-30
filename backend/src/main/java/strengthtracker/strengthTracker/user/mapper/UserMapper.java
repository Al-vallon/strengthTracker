package strengthtracker.strengthTracker.user.mapper;

import org.springframework.stereotype.Component;
import strengthtracker.strengthTracker.user.User;
import strengthtracker.strengthTracker.user.dto.UserDto;

@Component
public class UserMapper {
  public UserDto toDto(User user) {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setUsername(user.getUsername());
    dto.setEmail(user.getEmail());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    return dto;
  }
}
