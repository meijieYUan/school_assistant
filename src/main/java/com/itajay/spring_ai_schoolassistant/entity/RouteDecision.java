package com.itajay.spring_ai_schoolassistant.entity;

import java.util.List;

public record RouteDecision(
        List<Domain> domains,   //需要调用的不同业务域
        String finalAnswer
) {}
