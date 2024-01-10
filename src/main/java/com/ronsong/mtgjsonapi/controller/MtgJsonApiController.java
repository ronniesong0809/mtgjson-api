package com.ronsong.mtgjsonapi.controller;

import com.mongodb.bulk.BulkWriteResult;
import com.ronsong.mtgjsonapi.model.CardSet;
import com.ronsong.mtgjsonapi.service.MtgJsonApiService;
import com.ronsong.mtgjsonapi.service.MtgJsonEtlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MtgJsonApiController {
    private final MtgJsonApiService mtgJsonApiService;
    private final MtgJsonEtlService mtgJsonEtlService;

    @GetMapping("/all")
    public ResponseEntity<Page<CardSet>> getCards(
            @RequestParam(value = "page", required = false) int page
    ) {
        Page<CardSet> cards = mtgJsonApiService.getCards(page);

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/card")
    public ResponseEntity<List<CardSet>> getCard(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "mana", required = false) Integer mana,
            @RequestParam(value = "power", required = false) String power,
            @RequestParam(value = "toughness", required = false) String toughness,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "setCode", required = false) String setCode,
            @RequestParam(value = "types", required = false) String types,
            @RequestParam(value = "subtypes", required = false) String subtypes
    ) {
        List<CardSet> card = mtgJsonApiService.getCard(name, mana, power, toughness, text, setCode, types, subtypes);
        log.info("GET /card");

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping("/save")
    public ResponseEntity<BulkWriteResult> save() {
        log.info("GET /save");

        BulkWriteResult result = mtgJsonEtlService.save();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
