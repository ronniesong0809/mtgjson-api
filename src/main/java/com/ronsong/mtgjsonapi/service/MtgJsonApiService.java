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

    public ArrayList<CardSet> getCard(String name) {
        ArrayList<CardSet> cards = getCards();
        return cards.stream()
                .filter(card -> contains(card.getName(), name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static boolean contains(String wantedStr, String source) {
        return Pattern.compile(Pattern.quote(source), Pattern.CASE_INSENSITIVE).matcher(wantedStr).find();
    }
}
