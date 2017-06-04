package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import java.io.Serializable;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/06/02
 *     desc   : 实体类基类，项目中常用作解析加密网络返回等统一处理
 *              序列化，便于JVM停止运行时保存数据
 *     version: 1.0
 * </pre>
 */

public abstract class AbsBaseEntity implements Serializable {
    private String code;
    private String message;
    private String sign;

    /**这里做手动添加实体类数据**/
    public abstract void parseData ();

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
