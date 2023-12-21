package com.ronsong.mtgjsonapi.model;

import lombok.Data;

import java.util.Map;

@Data
public class AllPrintings {
    private Meta meta;
    private Map<String, Set> data;
}
