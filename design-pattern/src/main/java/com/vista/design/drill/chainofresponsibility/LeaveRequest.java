package com.vista.design.drill.chainofresponsibility;

import lombok.Data;

/**
 * 封装请假的基本信息
 *
 * @author WenTingTing by 2020/4/17
 */
@Data
public class LeaveRequest {
    private String empName;
    private int leaveDays;
    private  String reason;

    public LeaveRequest(String empName, int leaveDays, String reason) {
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }
}

