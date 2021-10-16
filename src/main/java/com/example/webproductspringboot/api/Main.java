package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.vo.SearchOrderVo;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class Main extends Thread {

    private static Date now = new Date();

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Long seconds = (new Date().getTime() - now.getTime())/1000;
            System.out.println(seconds);
            sleep(1000);
        }
    }


    public static void main(String[] args) {
//        final String uri = "http://localhost:8091/api/users";
//        HttpHeaders headers = new HttpHeaders();
//        RestTemplate restTemplate = new RestTemplate();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.set("X-COM-PERSIST", "NO");
//        headers.set("X-COM-LOCATION", "USA");
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        ResponseEntity<ResultDto> responseEntity = restTemplate
//                .exchange(uri, HttpMethod.GET, entity, ResultDto.class);
//        System.out.println(responseEntity.getBody().getData());

//        System.out.println(new BCryptPasswordEncoder().encode("1"));

//        Integer[] arr = {1, 2};
//        System.out.println(Arrays.toString(arr));

        Main main = new Main();
        main.start();


    }

}
