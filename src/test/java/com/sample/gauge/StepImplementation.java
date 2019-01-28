package com.sample.gauge;

import com.thoughtworks.gauge.*;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    private HashSet<Character> vowels;
    public static String tenv = System.getProperty("tenv");

    @BeforeSuite
    public void beforeSuite(ExecutionContext context){
        UserFinder userFinder = UserFinder.getUserFinder();
        System.out.println("test "+ System.getenv("tenv"));
        System.out.println("test "+ System.getenv("tenv1"));
        System.out.println("test "+ System.getenv("tenv2"));
    }


    @Step("Vowels in English language are <vowelString>.")
    public void setLanguageVowels(String vowelString) {
        vowels = new HashSet<>();
        for (char ch : vowelString.toCharArray()) {
            vowels.add(ch);
        }
    }

    @Step("The word <word> has <expectedCount> vowels.")
    public void verifyVowelsCountInWord(String word, int expectedCount) {
        int actualCount = countVowels(word);
        assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Step("Almost all words have vowels <wordsTable>")
    public void verifyVowelsCountInMultipleWords(Table wordsTable) {
        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            int expectedCount = Integer.parseInt(row.getCell("Vowel Count"));
            int actualCount = countVowels(word);

            assertThat(expectedCount).isEqualTo(actualCount);
        }
    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if (vowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }

    @AfterScenario
    public void afterScenario(){
        String a =null;
        Gauge.writeMessage("Hi "+ a);
        Gauge.writeMessage("Hi hello "+ getMessage());
    }

    @AfterSuite
    public void afterSuite(){
        String a =null;
        Gauge.writeMessage( a);
        Gauge.writeMessage("Hi hello "+ getMessage());
    }


    public String getMessage(){
        return null;
    }


}
