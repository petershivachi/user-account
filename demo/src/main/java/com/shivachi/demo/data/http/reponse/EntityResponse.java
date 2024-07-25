package com.shivachi.demo.data.http.reponse;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityResponse {
    @Builder.Default
    private Integer statusCode = null;

    @Builder.Default
    private String message = null;
}
