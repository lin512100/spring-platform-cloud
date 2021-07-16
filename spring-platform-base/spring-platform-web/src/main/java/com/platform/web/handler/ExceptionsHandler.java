package com.platform.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 默认异常校验类
 * @author lin512100
 * @date 2021/6/24
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    // /**
    //  * 参数校验异常处理
    //  */
    // @ExceptionHandler(ConstraintViolationException.class)
    // public ResultData<String> handleExceptions(ConstraintViolationException ex) {
    //     return ResultData.error(SystemErrorCode.PARAM_ERROR, ex.getMessage(), ex.toString());
    // }
    //
    // /**
    //  * 业务异常处理
    //  */
    // @ExceptionHandler(SystemException.class)
    // public ResultData<Object> handleAppException(SystemException ex) {
    //     log.info("handleAppException..error:", ex);
    //     return ResultData.error(ex.getCode(), ex.getMessage(), ex.getData());
    // }
    //
    // @ExceptionHandler(PersistenceException.class)
    // public ResultData<Object> handleParamsException(PersistenceException ex) {
    //     log.info("handleAppException..error:", ex);
    //     return ResultData.error(SystemErrorCode.UNKNOWN_EXCEPTION,ex.getMessage());
    // }
}
