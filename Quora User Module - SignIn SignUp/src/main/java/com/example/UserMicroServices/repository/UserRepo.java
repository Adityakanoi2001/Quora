package com.example.UserMicroServices.repository;

import com.example.UserMicroServices.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    User findUserNameByUserId(String userId);

}
