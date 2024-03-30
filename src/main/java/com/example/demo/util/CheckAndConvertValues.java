package com.example.demo.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CheckAndConvertValues {
    public static String getStringReturnFromCheckValueAsText(JsonNode jsonNode) {
        Optional<JsonNode> optional = Optional.ofNullable(jsonNode);
        return optional
                .map(JsonNode::asText)
                .orElse("");
    }
    public static BigDecimal getBigDecimalReturnFromCheckValue(JsonNode jsonNode) {
        Optional<JsonNode> optional = Optional.ofNullable(jsonNode);
        return optional.map(node -> {
            try {
                return new BigDecimal(
                        node.asText())
                        .setScale(4, BigDecimal.ROUND_HALF_UP
                        );
            } catch (NumberFormatException e) {
                return BigDecimal.ZERO;
            }
        }).orElse(BigDecimal.ZERO);
    }

    public static boolean getBooleanReturnFromCheckValue(JsonNode jsonNode) {
        Optional<JsonNode> optional = Optional.ofNullable(jsonNode);
        return optional
                .map(node -> node.isBoolean() ? node.asBoolean() : false)
                .orElse(false);
    }

    public static LocalDateTime convertDateTime(String localDateStr) {
        return Optional.ofNullable(localDateStr)
                .filter(str -> !str.isEmpty())
                .map(str -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    return LocalDateTime.parse(str, formatter);
                })
                .orElse(null);
    }

    public static int getIntReturnFromCheckValue(JsonNode jsonNode) {
        Optional<JsonNode> optional = Optional.ofNullable(jsonNode);
        return optional
                .map(node -> node.isInt() ? node.asInt() : 0)
                .orElse(0);
    }

    public static Long getLongReturnFromCheckValue(JsonNode jsonNode) {
        Optional<JsonNode> optional = Optional.ofNullable(jsonNode);
        Long longValue = 0l;
        if ( optional.isPresent()) {
            longValue = jsonNode.longValue();
        }
        return longValue;
    }
}
