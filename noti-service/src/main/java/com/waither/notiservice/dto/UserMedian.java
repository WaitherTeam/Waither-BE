package com.waither.notiservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserMedian {

    public Double medianOf1And2;
    public Double medianOf2And3;
    public Double medianOf3And4;
    public Double medianOf4And5;
}
