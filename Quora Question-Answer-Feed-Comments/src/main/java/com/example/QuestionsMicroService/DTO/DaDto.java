package com.example.QuestionsMicroService.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DaDto
{
    public String actionType;
    public List<String> categories;
    public String platformId;
    public String postId;
    public String userId;
    private LocalDateTime actionTime;
}
