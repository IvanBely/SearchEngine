package searchengine.dto;

import lombok.Data;

@Data
public class PageDto {
    private String url;
    private String content;
    private int code;
}
