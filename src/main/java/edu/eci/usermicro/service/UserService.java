package edu.eci.usermicro.service;

import java.util.List;

import edu.eci.usermicro.dto.UserDto;
import edu.eci.usermicro.entities.User;

public interface UserService {
    User create(User user);

    User findById(String id);

    List<User> getAll();

    boolean deleteById(String id);

    User update(User user, String userId);

    UserDto fromEntityToDto(User user);

    List<UserDto> fromEntityToDtos(List<User> user);

    User fromDtoToEntity(UserDto userDto); 
    
    
}
