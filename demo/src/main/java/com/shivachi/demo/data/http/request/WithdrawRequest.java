package com.shivachi.demo.data.http.request;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequest {
    private String userId;
    private Double amount;
}
