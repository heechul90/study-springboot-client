package study.springboot.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springboot.client.dto.Request;
import study.springboot.client.dto.UserResponse;
import study.springboot.client.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {

    private final RestTemplateService restTemplateService;

    @GetMapping(value = "/hello")
    public Request<UserResponse> hello() {
        //return restTemplateService.hello();
        //return restTemplateService.post();
        //return restTemplateService.exchange();
        return restTemplateService.genericExchange();
    }
}
