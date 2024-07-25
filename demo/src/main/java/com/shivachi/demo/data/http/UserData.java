package com.shivachi.demo.data.http;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    @Builder.Default
    private String userId = null;

    @Builder.Default
    private String name = null;

    @Builder.Default
    @CreationTimestamp
    private Timestamp createdAt = null;

    @Builder.Default
    private Boolean status = null;

    @Builder.Default
    private Double balance = null;
}
