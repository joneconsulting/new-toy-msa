package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.client.PaymentServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponsePayment;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    Environment env;

    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;

    RestTemplate restTemplate;

    OrderServiceClient orderServiceClient;
    PaymentServiceClient paymentServiceClient;

    public UserServiceImpl(Environment env, UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder, RestTemplate restTemplate,
                           OrderServiceClient orderServiceClient, PaymentServiceClient paymentServiceClient) {
        this.env = env;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
        this.paymentServiceClient = paymentServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null)
            throw new UsernameNotFoundException(username + ": not found");

        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
//        userEntity.setEncryptedPwd("encrypted_password");
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null)
            throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        List<ResponseOrder> orderList = orderServiceClient.getOrders(userId);
        // 방법 1: For each code
        for (ResponseOrder order : orderList) {

            ResponsePayment responsePayment =
                    paymentServiceClient.getPayment(order.getOrderId());

            if (responsePayment != null) {
                order.setPaymentStatus(responsePayment.getStatus());
                order.setPaymentDate(responsePayment.getCreatedAt());
            }
        }
        userDto.setOrders(orderList);

        // 방법 2: WebFlux code
//        List<ResponseOrder> enrichedOrderList = Flux.fromIterable(orderList)
//                .flatMap(order ->
//                        Mono.fromCallable(() -> paymentServiceClient.getPayment(order.getOrderId()))
//                                .subscribeOn(Schedulers.boundedElastic())
//                                .doOnNext(payment -> {
//                                    if (payment != null) {
//                                        order.setPaymentStatus(payment.getStatus());
//                                    }
//                                })
//                                .thenReturn(order)
//                                .onErrorReturn(order)
//                )
//                .collectList()
//                .block();
//        userDto.setOrders(enrichedOrderList);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(userEntity, UserDto.class);
        return userDto;
    }
}
