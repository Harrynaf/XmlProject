package com.xmlproject.main;

import com.xmlproject.cases.UseCase;

public class Main {
    public static void main(String[] args) {
        UseCase useCase = new UseCase();
        try {
            useCase.parsingValidationCase();
            useCase.readAndWriteCase();
            useCase.getStatisticsCase();
            useCase.xmlByStealingParagraphsCase();
            useCase.getAllCase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
