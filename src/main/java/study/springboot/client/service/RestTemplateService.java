package study.springboot.client.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import study.springboot.client.dto.UserResponse;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost/api/server/hello

    public UserResponse hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/server/hello")
                .queryParam("name", "spring1")
                .queryParam("age", 33)
                .encode()
                .build()
                .toUri();

        System.out.println("uri = " + uri.toString());

        RestTemplate template = new RestTemplate();
        ResponseEntity<UserResponse> result = template.getForEntity(uri, UserResponse.class);
        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();

    }
}
