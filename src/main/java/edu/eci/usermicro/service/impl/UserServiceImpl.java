package edu.eci.usermicro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.eci.usermicro.dto.UserDto;
import edu.eci.usermicro.entities.User;
import edu.eci.usermicro.service.UserService;

//@Service
public class UserServiceImpl implements UserService {

    private HashMap<String,User> users;
    private final AtomicLong counter = new AtomicLong();
    public UserServiceImpl(){
        users = new HashMap<>();
        users.put("24", new User(Long.toString(counter.incrementAndGet()),"hola","hola@hotmail.com","adios","ayer"));
        users.put("27",
                new User(Long.toString(counter.incrementAndGet()), "andres", "andres@hotmail.com", "pico", "hoy"));
    }

    @Override
    public User create(User user) {
        users.put(Long.toString(counter.incrementAndGet()), user);

        return users.get(Long.toString(users.size()));
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(String id) {
        boolean flag;
        if(users.containsKey(id)){
            users.remove(id);
            flag = true;
        }else{flag = false;}
        return flag;
    }

    @Override
    public User update(User user, String userId) {
        return users.put(userId, user);
    }

    @Override
    public UserDto fromEntityToDto(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getLastName(), user.getCreatedAt());
        return userDto;
    }

    @Override
    public List<UserDto> fromEntityToDtos(List<User> user){
        return user.stream().map(x -> fromEntityToDto(x)).collect(Collectors.toList());
    }
    
    @Override
    public User fromDtoToEntity(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getLastName(),
                userDto.getCreatedAt());
        return user;
    }

  
}
