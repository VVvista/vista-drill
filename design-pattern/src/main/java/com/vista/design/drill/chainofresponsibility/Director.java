package com.vista.design.drill.chainofresponsibility;

/**
 * 具体处理类-主任
 * 实现公共方法的处理逻辑
 *
 * @author WenTingTing by 2020/4/17
 */
public class Director extends Leader {

    public Director(String name) {
        super(name);
    }

    /**
     * 实现公共方法处理逻辑
     * @param request
     */
    @Override
    public void handleRequest(LeaveRequest request) {
        int leaveDays = request.getLeaveDays();
        if (leaveDays < 3) {
            System.out.println("主任：" + this.name + "审批通过！");
            System.out.println("员工：" + request.getEmpName() + "请假，天数：" + request.getLeaveDays() + ",原因：" + request.getReason());
        } else {
            if (nextLeader != null) {
                nextLeader.handleRequest(request);
            }else{
                System.out.println("主任：" + this.name + "审批拒绝！！");
            }

        }
    }
}
