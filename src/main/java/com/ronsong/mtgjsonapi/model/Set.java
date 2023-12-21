package com.ronsong.mtgjsonapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Set {
    private Integer baseSetSize;
    private String block;
    // private String booster;
    private List<CardSet> cards;
    private Integer cardsphereSetId;
    private String code;
    private String codeV3;
    // private String decks;
    private Boolean isForeignOnly;
    private Boolean isFoilOnly;
    private Boolean isNonFoilOnly;
    private Boolean isOnlineOnly;
    private Boolean isPaperOnly;
    private Boolean isPartialPreview;
    private String keyruneCode;
    private List<String> languages;
    private Integer mcmId;
    private Integer mcmIdExtras;
    private String mcmName;
    private String mtgoCode;
    private String name;
    private String parentCode;
    private String releaseDate;
    // private String sealedProduct;
    private Integer tcgplayerGroupId;
    // private String tokens;
    private String tokenSetCode;
    private Integer totalSetSize;
    // private String translations;
    private String type;
}
