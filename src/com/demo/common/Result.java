package com.demo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -4203507528681658133L;
    private Integer code;

    private String msg;

    private Object data;

}
