package com.vista.design.drill.chainofresponsibility;

import lombok.Data;

/**
 * 抽象处理类
 * 定义公共的处理方法
 *
 * @author WenTingTing by 2020/4/17
 */
@Data
public abstract class Leader {
    protected String name;
    protected Leader nextLeader;//责任链上的后继对象

    public Leader(String name) {
        this.name = name;
    }
    //设定责任链上的后继对象
    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }

    /**
     * 公共的处理方法
     * @param request
     */
    public abstract void handleRequest(LeaveRequest request);

}
