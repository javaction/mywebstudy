/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 通用返回结果对象
 * @author Administrator
 */
@XmlRootElement
public class Result {
    
    private String result;//操作结果
    private String message;//操作信息
    
    public Result() {
    }
    
    public Result(String result) {
        this.result = result;
    }

    public Result(String result, String message) {
        this.result = result;
        this.message = message;
    }
    
    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
