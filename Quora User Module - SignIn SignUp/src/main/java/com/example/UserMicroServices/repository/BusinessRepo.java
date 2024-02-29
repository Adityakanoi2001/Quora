package com.example.UserMicroServices.repository;

import com.example.UserMicroServices.entity.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepo extends MongoRepository<Business ,String> {
}
