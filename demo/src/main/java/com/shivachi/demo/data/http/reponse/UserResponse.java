package com.shivachi.demo.data.http.reponse;

import com.shivachi.demo.data.http.UserData;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @Builder.Default
    private Integer statusCode = HttpStatus.NOT_FOUND.value();

    @Builder.Default
    private String message = HttpStatus.NOT_FOUND.getReasonPhrase();

    @Builder.Default
    List<UserData> users = null;
}
