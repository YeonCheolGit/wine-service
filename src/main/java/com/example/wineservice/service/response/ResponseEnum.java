package com.example.wineservice.service.response;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    DEFAULT_SUCCESS(true, 0, "성공했습니다.", 200);

    private final boolean check;
    private final int code;
    private final String msg;
    private final int status;

    ResponseEnum(boolean check, int code, String msg, int status) {
        this.check = check;
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

}
