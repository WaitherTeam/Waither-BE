package com.waither.notiservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    /**
     * <h2>userData 동기화 토픽</h2>
     * @Producer : user-service
     * @Consumer : noti-service
     * @MessageFormat : "{"userId" : "{사용자 ID}", "level" : "{레벨}", "temperature" : "{온도}" }"
     * @example : "{"userId" : "23", "level" : "2", "temperature" : "14.5" }"
     * @Description : user-service의 userData 테이블과 noti-service의 테이블의 데이터를 동기화 하기 위해 사용합니다.
     */
    @Bean
    public NewTopic userDataTopic(){
        return TopicBuilder.name("user-data")
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * <h2>외출 시간 알림 토픽</h2>
     * @Producer : user-service
     * @Consumer : noti-service
     * @MessageFormat : "{사용자 ID}, {사용자 맞춤 예보 on/off 여부}"
     * @example : "23, true"
     * @Description : 외출 시간 푸시 알림을 생성하기 위한 토픽입니다. 알림을 보낼 시간이 되면 메세지를 발행합니다.
     */
    @Bean
    public NewTopic GoOutAlarmTopic(){
        return TopicBuilder.name("alarm-go-out")
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * <h2>바람 세기 알림 토픽</h2>
     * @Producer : weather-service
     * @Consumer : noti-service
     * @MessageFormat : "{바람 세기}"
     * @example : "14.2"
     * @Description : 매 주기 바람 세기를 메세지에 담아 발행합니다.
     */
    @Bean
    public NewTopic WindAlarmTopic(){
        return TopicBuilder.name("alarm-wind")
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * <h2>강설 정보 알림 토픽</h2>
     * @Producer : weather-service
     * @Consumer : noti-service
     * @MessageFormat : "{강수량}" (확인 필요)
     * @example : "0.03"
     * @Description : 매 주기 강설 정보를 메세지에 담아 발행합니다.
     */
    @Bean
    public NewTopic SnowAlarmTopic(){
        return TopicBuilder.name("alarm-snow")
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * <h2>기상 특보 알림 토픽</h2>
     * @Producer : weather-service
     * @Consumer : noti-service
     * @MessageFormat : "{기상 특보 메세지}"
     * @example : "서울 전역 강풍 특보 발령"
     * @Description : 기상 특보 메세지를 실시간으로 담아 발행합니다.
     */
    @Bean
    public NewTopic climateAlarmTopic(){
        return TopicBuilder.name("alarm-climate")
                .partitions(1)
                .replicas(1)
                .build();
    }


}
