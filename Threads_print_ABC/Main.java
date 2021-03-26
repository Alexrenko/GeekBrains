package FirstSemestr.Java3.Lesson_4.Threads_print_ABC;

public class Main {
    public static void main(String[] args) {
        char[] letters = new char[] {'A', 'B', 'C'}; //список букв для печати
        int count = 5;  //количество циклов печати
        LettersPrinter printer = new LettersPrinter(letters, count);
        printer.printLetters();
    }
}

