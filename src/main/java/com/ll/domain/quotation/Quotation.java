package com.ll.domain.quotation;


import lombok.Getter;
import lombok.Setter;

public class Quotation {
    @Getter
    private int id;
    @Getter
    @Setter
    private String saying;
    @Getter
    @Setter
    private String author;


    public Quotation(int idx, String saying, String author) {
        this.id = idx;
        this.saying = saying;
        this.author = author;
    }


}