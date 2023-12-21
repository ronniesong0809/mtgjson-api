package com.ronsong.mtgjsonapi.controller;

import com.ronsong.mtgjsonapi.model.CardSet;
import com.ronsong.mtgjsonapi.service.MtgJsonApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MtgJsonApiController {
    private final MtgJsonApiService MtgJsonApiService;

    @GetMapping("/all")
    public ResponseEntity<ArrayList<CardSet>> getCards() {
        ArrayList<CardSet> cards = MtgJsonApiService.getCards();

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/card")
    public ResponseEntity<ArrayList<CardSet>> getCard(@RequestParam(value = "name") String name) {
        ArrayList<CardSet> card = MtgJsonApiService.getCard(name);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
