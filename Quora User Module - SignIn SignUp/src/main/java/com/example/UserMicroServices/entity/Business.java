package com.example.UserMicroServices.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = Business.collection_name)
@Setter
@Getter
public class Business {
    public static final String collection_name="Business";
    @Id
    private String businessId;
    private String businessName;
    private String bio;
    private List<String> moderatorIds;
    private String image;
}
