package com.shivachi.demo.integ;

import com.shivachi.demo.data.http.UserData;
import com.shivachi.demo.data.http.reponse.EntityResponse;
import com.shivachi.demo.data.http.reponse.UserDataResponse;
import com.shivachi.demo.data.http.reponse.UserResponse;
import com.shivachi.demo.data.http.request.UpdateUserRequest;
import com.shivachi.demo.data.http.request.WithdrawRequest;
import com.shivachi.demo.model.UserAccount;
import com.shivachi.demo.repo.UserAccountRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepo userAccountRepo;

    public UserResponse findAllUsers(){
        AtomicReference<UserResponse> response = new AtomicReference<>();

        List<UserAccount> userAccounts = userAccountRepo.findAll();

        if(!userAccounts.isEmpty()) {
            List<UserData> userDataList = new ArrayList<>();

            userAccounts.forEach(userAccount -> {
                UserData userData = UserData.builder()
                        .name(userAccount.getName())
                        .userId(userAccount.getUserId())
                        .createdAt(userAccount.getCreatedAt())
                        .status(userAccount.getStatus())
                        .balance(userAccount.getBalance())
                        .build();

                userDataList.add(userData);
            });

            response.set(UserResponse.builder().statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).users(userDataList).build());
        }else {
            response.set(UserResponse.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase()).build());
        }

        return response.get();
    }

    public UserDataResponse getUserDetails (String userId){
        AtomicReference<UserDataResponse> response = new AtomicReference<>();

        userAccountRepo.findByUserId(userId).ifPresentOrElse(userAccount -> {
            UserData userData = UserData.builder()
                    .name(userAccount.getName())
                    .userId(userAccount.getUserId())
                    .createdAt(userAccount.getCreatedAt())
                    .status(userAccount.getStatus())
                    .balance(userAccount.getBalance())
                    .build();

            response.set(UserDataResponse.builder().statusCode(HttpStatus.OK.value()).data(userData).build());

        }, () -> {
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(userId);
            userAccount.setBalance(0.0);

            userAccountRepo.save(userAccount);

            response.set(UserDataResponse.builder().statusCode(HttpStatus.OK.value()).message(String.format("Details for %s userid incomplete, please update and revalidate", userId)).build());
        });

        return response.get();
    }

    public EntityResponse updateUser(UpdateUserRequest request){
        AtomicReference<EntityResponse> response = new AtomicReference<>();

        userAccountRepo.findByUserId(request.getUserId()).ifPresentOrElse(userAccount -> {
            AtomicReference<UserAccount> myUser = new AtomicReference<>(userAccount);
            userAccount.setName(request.getName());
            userAccount.setBalance(request.getBalance());

            userAccountRepo.save(myUser.get());

            response.set(EntityResponse.builder().statusCode(HttpStatus.OK.value()).message(String.format("Details for %s userid updated", request.getUserId())).build());
        }, () -> {

            response.set(EntityResponse.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(String.format("User with id %s not found", request.getUserId())).build());
        });

        return response.get();
    }

    public UserDataResponse withdrawAmount(WithdrawRequest request){
        AtomicReference<UserDataResponse> response = new AtomicReference<>();

        userAccountRepo.findByUserId(request.getUserId()).ifPresentOrElse(userAccount -> {
            AtomicReference<UserAccount> myUser = new AtomicReference<>(userAccount);

            UserData userData = UserData.builder()
                    .name(userAccount.getName())
                    .userId(userAccount.getUserId())
                    .createdAt(userAccount.getCreatedAt())
                    .status(userAccount.getStatus())
                    .balance(userAccount.getBalance() - request.getAmount())
                    .build();

            myUser.set(userAccount);

            userAccountRepo.save(myUser.get());

            response.set(UserDataResponse.builder().statusCode(HttpStatus.OK.value()).data(userData).build());

        }, () -> {

            response.set(UserDataResponse.builder().statusCode(HttpStatus.OK.value()).message(String.format("Details for %s userid not found", request.getUserId())).build());
        });

        return response.get();
    }
}
