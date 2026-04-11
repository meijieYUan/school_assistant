package com.itajay.spring_ai_schoolassistant.Tools.wetherTool;

public record GeoResult(
    double latitude,
    double longitude,
    String name
) {}