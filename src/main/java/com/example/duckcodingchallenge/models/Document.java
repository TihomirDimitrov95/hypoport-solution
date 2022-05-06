package com.example.duckcodingchallenge.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Document {

    private String id;
    private String name;
    private Long size;
    private Type type;
    private List<String> categories;
    private Boolean deleted;

    private String createdAt;

    private String modifiedAt;

    public enum Type {
        PDF, IMAGE
    }

}