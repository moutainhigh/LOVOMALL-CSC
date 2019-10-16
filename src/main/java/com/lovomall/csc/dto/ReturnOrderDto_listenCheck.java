package com.lovomall.csc.dto;

import lombok.Data;

@Data
public class ReturnOrderDto_listenCheck {



    private String checkDate;
    private String checkResult;
    private String rejectCause;
    private String returnOrderInfoId;





    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public String getReturnOrderInfoId() {
        return returnOrderInfoId;
    }

    public void setReturnOrderInfoId(String returnOrderInfoId) {
        this.returnOrderInfoId = returnOrderInfoId;
    }
}