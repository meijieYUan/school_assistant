package com.itajay.spring_ai_schoolassistant.Tools.wetherTool;

import lombok.extern.log4j.Log4j2;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Log4j2
@Component
public class WeatherTool{
    RestClient geoClient = RestClient.builder()
            .baseUrl("https://geocoding-api.open-meteo.com/v1")
            .build();
    private final RestClient wetherClient = RestClient.builder()
            .baseUrl("https://api.open-meteo.com/v1")
            .build();
    /**
     *
     * @param wetherRequest 查询某个城市的天气
     * @return  返回城市近期的天气情况
     */
    @Tool(name = "queryWeather",description = "查询某个地方近期的天气情况，返回内容包含最高温度和最低温度",
            returnDirect = true)
    public WetherResponse queryWeather(@ToolParam(description = "查询天气的请求参数") WetherRequest wetherRequest) {

        log.info("收到查询天气请求，开始地理解析！");
        //根据城市名获取到经纬度
        try {
            String GeoBaseurl="/search?name={city}&count=1&language=zh&format=json";
            GeoResponse geoResponse = geoClient.get()
                    .uri(GeoBaseurl,wetherRequest.city())
                    .retrieve()
                    .body(GeoResponse.class);
            if(geoResponse==null||geoResponse.results()==null||geoResponse.results().isEmpty())
            {
                WetherResponse response = new WetherResponse();
                response.setRemark("查询天气失败");
                return response;
            }
            log.info("地理解析成功" + wetherRequest);

            GeoResult geoResult = geoResponse.results().get(0);
            log.info("开始查询天气");
            // 使用 {} 参数占位
            String WetherBaseUrl= "/forecast?latitude={latitude}&longitude={longtitude}&daily=temperature_2m_max,temperature_2m_min&timezone=Asia/Shanghai&forecast_days={forecast_days}&models=cma_grapes_global";

            WetherResponse body = wetherClient.get()
                    .uri(WetherBaseUrl, geoResult.latitude(), geoResult.longitude(), wetherRequest.days())
                    .retrieve()
                    .body(WetherResponse.class);
            if(body!=null) {
                body.setCity(geoResult.name());
                return body;
            }
                WetherResponse response = new WetherResponse();
                 response.setRemark("查询天气失败");
                 return response;
        } catch (Exception e) {
            throw new RuntimeException("查询天气异常！");
        }
    }
}


