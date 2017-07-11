package com.my.payment.base.exception;

import java.util.List;

public class BusinessException extends BaseException {

    private static final long serialVersionUID = -2598217010733719435L;

    public BusinessException() {
        super();
    }

    public BusinessException(String errCode, List<String> params) {
        super(errCode, params);
    }

    public BusinessException(String errCode, String[] params) {
        super(errCode, params);
    }

    public BusinessException(String errCode, String message) {
        super(errCode, message);
    }

    public BusinessException(String errCode) {
        super(errCode);
    }

}
