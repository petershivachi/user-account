package com.shivachi.demo.handler;


import com.shivachi.demo.data.http.reponse.EntityResponse;
import com.shivachi.demo.data.http.reponse.UserDataResponse;
import com.shivachi.demo.data.http.reponse.UserResponse;
import com.shivachi.demo.data.http.request.UpdateUserRequest;
import com.shivachi.demo.data.http.request.WithdrawRequest;
import com.shivachi.demo.integ.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user-account")
@RequiredArgsConstructor
public class UserAccountHandler {

    private final UserAccountService userAccountService;

    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<UserResponse>> getAllUserAccounts(){
        return Mono.just(ResponseEntity.ok().body(userAccountService.findAllUsers()));
    }

    @RequestMapping(
            path = "/all{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<UserDataResponse>> getUserAccountDetails(@PathVariable String userId){
        return Mono.just(ResponseEntity.ok().body(userAccountService.getUserDetails(userId)));
    }

    @RequestMapping(
            path = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EntityResponse>> updateUserDetails(@RequestBody UpdateUserRequest request){
        return Mono.just(ResponseEntity.ok().body(userAccountService.updateUser(request)));
    }

    @RequestMapping(
            path = "/withdraw",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<UserDataResponse>> withdraw(@RequestBody WithdrawRequest request){
        return Mono.just(ResponseEntity.ok().body(userAccountService.withdrawAmount(request)));
    }
}
