package com.waither.notiservice.service;

import com.waither.notiservice.domain.UserData;
import com.waither.notiservice.dto.AlarmGoOutDto;
import com.waither.notiservice.repository.UserDataRepository;
import com.waither.notiservice.utils.TemperatureUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerService {

    private final UserDataRepository userDataRepository;

    /**
     * 외출 알림 Listener
     * */
    @KafkaListener(topics = "alarm-go-out", containerFactory = "alarmGoOutKafkaListenerContainerFactory")
    public void consumeGoOutAlarm(AlarmGoOutDto alarmGoOutDto) {
        String resultMessage = "";
        String token = alarmGoOutDto.getToken();
        boolean isUserAlert = alarmGoOutDto.isUserAlert();
        String nickname = alarmGoOutDto.getUserName();


        log.info("[ Kafka Listener ] 사용자 아침 알림");
        log.info("[ Kafka Listener ] User token : --> {}", token);
        log.info("[ Kafka Listener ] userAlert : --> {}", isUserAlert);

        //TODO : 하루 날씨 정보 가져오기
        double avgTemp = 2.5;

        resultMessage += nickname + "님, ";


        resultMessage += TemperatureUtils.createUserDataMessage(alarmGoOutDto.getUserMedian(), avgTemp);

        //TODO : 날씨 요약
        resultMessage += "오후 6시에 비가 올 예정이예요. 우산을 챙겨가세요.";

        //TODO : 푸시알림 전송

    }

    /**
     * 바람 세기 알림 Listener
     * */
    @KafkaListener(topics = "alarm-wind")
    public void consumeWindAlarm(@Payload String message) {
        String resultMessage = "";
        Long windStrength = Long.valueOf(message); //바람세기

        log.info("[ Kafka Listener ] 바람 세기");
        log.info("[ Kafka Listener ] Wind Strength : --> {}", windStrength);

        //TODO : 알림 보낼 사용자 정보 가져오기 (Redis)
        List<Long> userIds = new ArrayList<>();

        //TODO : 바람 세기 알림 멘트 정리
        resultMessage += "현재 바람 세기가 " + windStrength + "m/s 이상입니다. 강풍에 주의하세요.";

        //TODO : 푸시알림 전송
        String finalResultMessage = resultMessage;
        userIds.forEach(id ->{

            System.out.println("[ 푸시알림 ] 바람 세기 알림");
            System.out.printf("[ 푸시알림 ] userId ---> {%d}", id);
            System.out.printf("[ 푸시알림 ] message ---> {%s}", finalResultMessage);
        });

    }

    /**
     * 기상 특보 알림 Listener
     * */
    @KafkaListener(topics = "alarm-climate")
    public void consumeClimateAlarm(@Payload String message) {
        String resultMessage = "";

        log.info("[ Kafka Listener ] 기상 특보");

        //TODO : 알림 보낼 사용자 정보 가져오기 (Redis)
        List<Long> userIds = new ArrayList<>();

        resultMessage += "[기상청 기상 특보] " + message;

        //TODO : 푸시알림 전송
        String finalResultMessage = resultMessage;
        userIds.forEach(id ->{

            System.out.println("[ 푸시알림 ] 기상 특보 알림");
            System.out.printf("[ 푸시알림 ] userId ---> {%d}", id);
            System.out.printf("[ 푸시알림 ] message ---> {%s}", finalResultMessage);
        });

    }
}
