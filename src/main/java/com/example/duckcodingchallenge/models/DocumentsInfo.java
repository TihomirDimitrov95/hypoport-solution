package com.example.duckcodingchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentsInfo {

    private long totalNumber;
    private long totalDeleted;
    private long totalSize;
    private double averageSize;

}
