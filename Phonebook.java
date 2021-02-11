package FirstSemestr.Java2.Lesson_3;

import java.util.*;

public class Phonebook {
    private final Map<String, Set<String>> book = new HashMap<>();

    public void add(String name, String phone) {
        if (book.containsKey(name)) {
            book.get(name).add(phone);
        } else {
            Set<String> set = new HashSet<>();
            set.add(phone);
            book.put(name, set);
        }
    }

    public Set<String> getPhones(String name) {
        if (book.containsKey(name)) {
            return book.get(name);
        }
        return Collections.EMPTY_SET;
    }
}
