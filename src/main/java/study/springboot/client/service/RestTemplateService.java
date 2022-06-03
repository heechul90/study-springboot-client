package study.springboot.client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import study.springboot.client.dto.Request;
import study.springboot.client.dto.UserRequest;
import study.springboot.client.dto.UserResponse;

import java.net.URI;

@Service
public class RestTemplateService {

    /**
     * http://localhost/api/server/hello
     */
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

    /**
     * http://localhost:9000/api/server/user/{userId}/name/{userName}
     */
    public UserResponse post() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "spring3")
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest request = new UserRequest("spring2", 35);
        RestTemplate template = new RestTemplate();
        ResponseEntity<UserResponse> response = template.postForEntity(uri, request, UserResponse.class);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.getBody() = " + response.getBody());

        return response.getBody();
    }

    /**
     * http://localhost:9000/api/server/user/{userId}/name/{userName}
     * header 추가
     */
    public UserResponse exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "spring3")
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest request = new UserRequest("spring2", 35);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(request);

        RestTemplate template = new RestTemplate();
        ResponseEntity<UserResponse> response = template.exchange(requestEntity, UserResponse.class);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.getBody() = " + response.getBody());
        return response.getBody();
    }

    public Request<UserResponse> genericExchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "spring3")
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest userRequest = new UserRequest("spring2", 35);

        Request request = new Request<>();
        request.setHeader(new Request.Header());
        request.setResponseBody(userRequest);

        RequestEntity<Request<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(request);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Request<UserResponse>> response
                = template.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

}
