package com.waither.notiservice.utils;

import com.waither.notiservice.domain.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TemperatureUtils {

    public static String createUserDataMessage(UserData userData, double temperature) {
        double medianBetween1And2 = (userData.getLevel1() + userData.getLevel2()) / 2;
        double medianBetween2And3 = (userData.getLevel2() + userData.getLevel3()) / 2;
        double medianBetween3And4 = (userData.getLevel3() + userData.getLevel4()) / 2;
        double medianBetween4And5 = (userData.getLevel4() + userData.getLevel5()) / 2;

        //TODO : 계절별 멘트 정리
        if (temperature < medianBetween1And2) {
            return "더우실거예요.";
        } else if (medianBetween1And2 <= temperature && temperature < medianBetween2And3) {
            return "조금 더우실거예요.";
        }else if (medianBetween2And3 <= temperature && temperature < medianBetween3And4) {
            return "좋을거예요.";
        }else if (medianBetween3And4 <= temperature && temperature < medianBetween4And5) {
            return "조금 추울거예요.";
        }else if (medianBetween4And5 <= temperature) {
            return "추울거예요.";
        }
        return null;
    }
}
