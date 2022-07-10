package com.example.marketback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class KeyWordRequest {

    private String id;
    private Integer page;
    private String keyWord;
    private String region;
}
