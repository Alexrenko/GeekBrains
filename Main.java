package FirstSemestr.Java2.Lesson_3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        firstTask();
        secondTask();
    }

    private static void firstTask() {
        String[] words = new String[] {"red", "orange", "yellow", "green", "ligh blue", "blue", "purple",
                "orange", "orange", "ligh blue", "purple", "green", "green", "green", "green"};

        Set<String> wordsSet = new HashSet<String>(Arrays.asList(words));
        System.out.println("Уникальные слова: " + wordsSet);

        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
        System.out.println("Подсчет слов: " + wordsMap);
    }

    private static void secondTask() {
        Phonebook phonebook = new Phonebook();

        phonebook.add("Ivanov", "89229334455");
        phonebook.add("Ivanov", "89119117865");
        phonebook.add("Petrov", "89455678900");

        System.out.println(phonebook.getPhones("Ivanov"));
        System.out.println(phonebook.getPhones("Petrov"));
        System.out.println(phonebook.getPhones("Sidorov"));
    }
}


