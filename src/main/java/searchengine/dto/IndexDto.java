package searchengine.dto;

import lombok.Data;

@Data
public class IndexDto {
    private long pageID;
    private long lemmaID;
    private float rank;
}
