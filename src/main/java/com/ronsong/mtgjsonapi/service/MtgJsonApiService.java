package com.ronsong.mtgjsonapi.service;

import com.ronsong.mtgjsonapi.model.CardSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MtgJsonApiService {

    public ArrayList<CardSet> getCards() {
        return Utils.getCardSet();
    }

    public ArrayList<CardSet> getCard(String name, Integer mana, String power, String toughness, String text, String setCode, String types, String subtypes) {
        ArrayList<CardSet> cards = Utils.getCardSet();
        return cards.stream()
                .filter(card -> name == null || Utils.contains(card.getName(), name))
                .filter(card -> mana == null || mana.equals(card.getConvertedManaCost()))
                .filter(card -> power == null || power.equals(card.getPower()))
                .filter(card -> toughness == null || toughness.equals(card.getToughness()))
                .filter(card -> text == null || Utils.contains(card.getText(), text))
                .filter(card -> setCode == null || setCode.equals(card.getSetCode()))
                .filter(card -> types == null || card.getTypes().contains(types))
                .filter(card -> subtypes == null || card.getSubtypes().contains(subtypes))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
