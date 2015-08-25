package com.levchenko.transformerShop.service.impl.exceptions;

/**
 * @author Alexey Levchenko
 */
public class DeletingShopWithEmployeesException extends RuntimeException {

    private Integer shopId;
    private String errMsg;


    public DeletingShopWithEmployeesException(Integer shopId, String errMsg) {
        this.shopId = shopId;
        this.errMsg = errMsg;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
