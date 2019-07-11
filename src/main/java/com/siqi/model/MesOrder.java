package com.siqi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MesOrder {
    private Integer id;

    private String orderId;

    private String orderClientname;

    private String orderProductname;

    private String orderContractid;

    private String orderImgid;

    private String orderMaterialname;

    private Date orderCometime;

    private Date orderCommittime;

    private Integer orderInventorystatus;

    private String orderSalestatus;

    private String orderMaterialsource;

    private Integer orderHurrystatus;

    private Integer orderStatus;

    private String orderRemark;

    private String orderOperator;

    private Date orderOperateTime;

    private String orderOperateIp;

  
}