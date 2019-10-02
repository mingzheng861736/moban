package com.mz.demo.exception;

import org.apache.shiro.authc.DisabledAccountException;

public class UserTypeAccountException extends DisabledAccountException {

    public UserTypeAccountException() {
        super();
    }

}
