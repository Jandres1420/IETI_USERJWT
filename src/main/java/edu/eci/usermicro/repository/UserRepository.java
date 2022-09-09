package edu.eci.usermicro.repository;

import edu.eci.usermicro.dto.LoginDto;
import edu.eci.usermicro.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);

}
