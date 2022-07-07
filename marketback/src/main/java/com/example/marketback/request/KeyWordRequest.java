package com.example.marketback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class KeyWordRequest {

    private Integer page;
    private String keyWord;
}
