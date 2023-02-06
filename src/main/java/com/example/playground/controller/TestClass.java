package com.example.playground.controller;

import com.example.playground.model.JsonRequest;
import com.example.playground.model.JsonResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestClass {

    @PostMapping("/testsearch")
    public @ResponseBody JsonResponse testJson(@RequestBody JsonRequest jsonRequest) throws Exception {

        System.out.println("request is ... "+jsonRequest);

        JsonResponse response = new JsonResponse();
        response.setSuccessYn("Y");

        System.out.println("response is ... "+response);
        return response;
    }


}
