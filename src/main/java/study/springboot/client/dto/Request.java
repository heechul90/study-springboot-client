package study.springboot.client.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Request<T> {

    private Header header;

    private T responseBody;

    @Getter
    @Setter
    @ToString
    public static class Header {
        private String responseCode;
    }
}
