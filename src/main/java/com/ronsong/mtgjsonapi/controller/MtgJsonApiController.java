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
    public ResponseEntity<ArrayList<CardSet>> getCard(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "mana", required = false) Integer mana,
            @RequestParam(value = "power", required = false) String power,
            @RequestParam(value = "toughness", required = false) String toughness,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "setCode", required = false) String setCode,
            @RequestParam(value = "types", required = false) String types,
            @RequestParam(value = "subtypes", required = false) String subtypes
    ) {
        ArrayList<CardSet> card = MtgJsonApiService.getCard(name, mana, power, toughness, text, setCode, types, subtypes);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
