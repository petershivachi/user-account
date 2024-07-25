package com.shivachi.demo.data.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String userId;
    private String name;
    private Double balance;
}
