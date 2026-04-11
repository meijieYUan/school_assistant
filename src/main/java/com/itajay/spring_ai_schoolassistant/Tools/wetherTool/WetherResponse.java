package com.itajay.spring_ai_schoolassistant.Tools.wetherTool;

import lombok.Data;

import java.io.Serializable;

// 返回天气信息
@Data
public class WetherResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String city;    //城市
    private DailyUnits daily_units; //天气数据单位
    private DailyData daily;    //天气数据
    private String remark;

    public WetherResponse(){
        daily_units = new DailyUnits();
        daily = new DailyData();
    }
}

@Data
class DailyUnits {  //单位
    private String time;
    private String temperature_2m_max;
    private String temperature_2m_min;
}

@Data
class DailyData {   //数据
    private String[] time;
    private String[] temperature_2m_min;
    private String[] temperature_2m_max;
}
