package com.ronsong.mtgjsonapi.service;

import com.ronsong.mtgjsonapi.model.CardSet;
import com.ronsong.mtgjsonapi.repository.MtgJsonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MtgJsonEtlService {
    private final MtgJsonRepository mtgJsonRepository;

    public void save() {
        ArrayList<CardSet> cardSet = Utils.getCardSet();
        int size = cardSet.size();

        for (int i = 0; i < cardSet.size(); i++) {
            CardSet set = cardSet.get(i);
            mtgJsonRepository.save(set);
            log.info("{}/{}, {} inserted", i, size, set.getName());
        }

    }
}
