package com.example.UserMicroServices.repository;

import com.example.UserMicroServices.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends MongoRepository<Follow,String> {
    void deleteByFollowerIdAndFollowingId(String followerId,String folloingId);
//    List<Follow> findByFollowingId(String followingId);
//    List<Follow> findByFollowerId(String followerId);
    List<Follow> findByFollowingId(String followingId);
    List<Follow> findByFollowerId(String followerId);
    List<Follow> findByFollowingIdAndConnection(String followingId,String type);
    List<Follow> findByFollowerIdAndConnection(String followerId,String type);
    Follow findByFollowerIdAndFollowingId(String followerId,String followingId);
}
