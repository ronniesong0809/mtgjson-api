package com.ronsong.mtgjsonapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronsong.mtgjsonapi.model.AllPrintings;
import com.ronsong.mtgjsonapi.model.CardSet;
import com.ronsong.mtgjsonapi.model.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MtgJsonApiService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<CardSet> getCards() {
        try {
            File allPrintings = new ClassPathResource("AllPrintings.json").getFile();
            AllPrintings card = objectMapper.readValue(allPrintings, AllPrintings.class);

            HashSet<Set> sets = new HashSet<>(card.getData().values());
            return sets.stream()
                    .map(Set::getCards)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<CardSet> getCard(String name, Integer mana, String power, String toughness, String text, String setCode, String types, String subtypes) {
        ArrayList<CardSet> cards = getCards();
        return cards.stream()
                .filter(card -> name == null || contains(card.getName(), name))
                .filter(card -> mana == null || mana.equals(card.getConvertedManaCost()))
                .filter(card -> power == null || power.equals(card.getPower()))
                .filter(card -> toughness == null || toughness.equals(card.getToughness()))
                .filter(card -> text == null || contains(card.getText(), text))
                .filter(card -> setCode == null || setCode.equals(card.getSetCode()))
                .filter(card -> types == null || card.getTypes().contains(types))
                .filter(card -> subtypes == null || card.getSubtypes().contains(subtypes))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static boolean contains(String wantedStr, String source) {
        return Pattern.compile(Pattern.quote(source), Pattern.CASE_INSENSITIVE).matcher(wantedStr).find();
    }
}
