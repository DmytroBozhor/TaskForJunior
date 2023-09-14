package org.example.game;

import java.util.*;

public class Main {

    public String findMostOptimalStep(double firstPlayerStartingChance, double secondPlayerStartingChance, int whoStartsFirst) {

        validateChances(firstPlayerStartingChance, secondPlayerStartingChance);

        int maxStepNumber = 20;
        int maxChanceToHit = 100;

        double stepValuationInChancesForFirstPlayer = (maxChanceToHit - firstPlayerStartingChance) / maxStepNumber;
        double stepValuationInChancesForSecondPlayer = (maxChanceToHit - secondPlayerStartingChance) / maxStepNumber;

        Map<Integer, Double> chancesMapFirstPlayer = new LinkedHashMap<>();
        Map<Integer, Double> chancesMapSecondPlayer = new LinkedHashMap<>();

        for (int i = 0; i < maxStepNumber; i++) {
            chancesMapFirstPlayer.put(i, firstPlayerStartingChance + (i * stepValuationInChancesForFirstPlayer));
            chancesMapSecondPlayer.put(i, secondPlayerStartingChance + (i * stepValuationInChancesForSecondPlayer));
        }

        Map<Integer, Double> chancesDifferenceMap = new LinkedHashMap<>();

        findChancesDifference(whoStartsFirst, chancesMapFirstPlayer, chancesMapSecondPlayer, chancesDifferenceMap);

        Optional<Double> maxDifferenceOptional = chancesDifferenceMap.values().stream().max(Double::compareTo);

        return "The best step to shoot for player {$player} is {$step}"
                .replace("{$player}", "" + whoStartsFirst)
                .replace("{$step}", "" + getKey(chancesDifferenceMap, maxDifferenceOptional.get()));
    }

    private static void findChancesDifference(int whoStartsFirst, Map<Integer, Double> chancesMapFirstPlayer, Map<Integer, Double> chancesMapSecondPlayer, Map<Integer, Double> chancesDifferenceMap) {
        if (whoStartsFirst == 1) {
            for (int i = 0; i < chancesMapFirstPlayer.size(); i++) {
                chancesDifferenceMap.put(i, chancesMapFirstPlayer.get(i) - chancesMapSecondPlayer.get(i));
            }
        } else if (whoStartsFirst == 2) {
            for (int i = 0; i < chancesMapFirstPlayer.size(); i++) {
                chancesDifferenceMap.put(i, chancesMapSecondPlayer.get(i) - chancesMapFirstPlayer.get(i));
            }
        } else throw new RuntimeException("Player not found");
    }

    private static void validateChances(double firstPlayerStartingChance, double secondPlayerStartingChance) {
        if (!(firstPlayerStartingChance >= 0.1 || firstPlayerStartingChance <= 0.3)) {
            throw new RuntimeException("Chances are not supported");
        }

        if (!(secondPlayerStartingChance >= 0.1 || secondPlayerStartingChance <= 0.3)) {
            throw new RuntimeException("Chances are not supported");
        }

        if (firstPlayerStartingChance == secondPlayerStartingChance) {
            throw new RuntimeException("Players have equal chances");
        }
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
