package com.greedy.matcat.order.dto;

import lombok.Data;

@Data
public class ImpDTO {

    private String imp_uid;
    private String merchant_uid;
    private String biz_email;
    private String pay_date;
    private int amount;
    private String card_type;
    private String status;
    private int proCode;

}
