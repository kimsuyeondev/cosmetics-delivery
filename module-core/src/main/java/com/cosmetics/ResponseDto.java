package com.cosmetics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private String resultCode;
    private String resultMsg;
    private String resultBody;
}
