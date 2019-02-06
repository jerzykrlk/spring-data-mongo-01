package com.example.demo;

public interface CupboardRepositoryCustom {
    void deleteCupByObject(String cupboardId, String cupId);

    void deleteCupByCriteria(String cupboardId, String cupId);

    void addCup(String s, Cup cup);
}
