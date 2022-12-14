package edu.eci.usermicro.service.impl;

import edu.eci.usermicro.dto.LoginDto;
import edu.eci.usermicro.dto.UserDto;
import edu.eci.usermicro.entities.User;
import edu.eci.usermicro.repository.UserRepository;
import edu.eci.usermicro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User create(User user){
        System.out.println("Entre a create "  + user.getName());
        /**
        if(user.getId().equals("")){
            int min_val = 10;
            int max_val = 1000;
            Random rand = new Random();
            int randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
            user.setId(String.valueOf(randomNum));
        }**/
        System.out.println("Estoy en create ");
        return userRepository.insert(user);
    }

    @Override
    public User findById(String id) {
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            return user;
        }else{return null;}
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        boolean flag;
        if (userRepository.existsById(id)){
            flag = true;
            userRepository.deleteById(id);
        }else{ flag = false;}
        return flag;
    }

    @Override
    public User update(User user, String userId) {
        if(userRepository.existsById(userId)){
            User oldUser = findById(userId);
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setCreatedAt(user.getCreatedAt());
            oldUser.setLastName(user.getLastName());
            return userRepository.save(oldUser);
        }return null;
    }

    @Override
    public UserDto fromEntityToDto(User user) {
        System.out.println("Entitie email " + user.getEmail());
        System.out.println("Entitie password " + user.getPasswordHash());
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getLastName(), user.getCreatedAt(),user.getPasswordHash());
        return userDto;
    }

    @Override
    public List<UserDto> fromEntityToDtos(List<User> user){
        return user.stream().map(x -> fromEntityToDto(x)).collect(Collectors.toList());
    }

    @Override
    public User fromDtoToEntity(UserDto userDto) {
        if(userDto.getPassword()!=null) {
            System.out.println("User DTO 1 email" + userDto.getEmail() );
            System.out.println("User DTO 1 password" + userDto.getPassword() );
            System.out.println("Password not null");
            User user = new User(userDto);
            System.out.println("se creo bien usuario en fromDtoToEntity "+user.getEmail());
            return user;
        }else{
            System.out.println("Password null");
            User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getLastName(),
                    userDto.getCreatedAt());
            return user;
        }



    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println("Hash password "+ user.getPasswordHash());
        return user;
    }


}
