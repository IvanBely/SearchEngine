package searchengine.dto;

import lombok.Data;

@Data
public class LemmaDto {
    private String lemma;
    private int frequency;
}
