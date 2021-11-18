package com.example.wineservice.service.response;

import com.example.wineservice.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseService {

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(ResponseEnum.DEFAULT_SUCCESS.isCheck());
        result.setCode(ResponseEnum.DEFAULT_SUCCESS.getCode());
        result.setMsg(ResponseEnum.DEFAULT_SUCCESS.getMsg());
        result.setStatus(ResponseEnum.DEFAULT_SUCCESS.getStatus());
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

}
