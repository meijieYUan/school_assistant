package com.itajay.spring_ai_schoolassistant.Tools.wetherTool;

import lombok.Data;

import java.util.List;

// 外层响应
public record GeoResponse(
        List<GeoResult> results
) {}
