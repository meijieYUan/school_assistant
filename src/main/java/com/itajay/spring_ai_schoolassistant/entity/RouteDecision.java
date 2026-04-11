package com.itajay.spring_ai_schoolassistant.entity;

import lombok.Data;

import java.util.List;

public record RouteDecision(
        List<TaskDetail> taskDetails,  //需要调用的不同业务域
        String analysis,
        String answer
) {}
