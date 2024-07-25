package com.shivachi.demo.data.http.reponse;

import com.shivachi.demo.data.http.UserData;
import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataResponse {
    @Builder.Default
    private Integer statusCode = null;

    @Builder.Default
    private String message = null;

    @Builder.Default
    private UserData data = null;
}
