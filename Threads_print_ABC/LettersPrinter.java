package FirstSemestr.Java3.Lesson_4.Threads_print_ABC;

public class LettersPrinter {
    private final char[] letters;
    private final int count;
    private final Object monitor = new Object();
    private volatile char currentLetter;

    public LettersPrinter(char[] letters, int count) {
        this.letters = letters;
        this.count = count;
        if (letters.length != 0) {
            this.currentLetter = letters[0];
        }
    }

    //Выводит в консоль буквы из массива letters по очереди, count раз
    public void printLetters() {
        if (letters.length != 0) {
            for (int i = 0; i < letters.length; i++) {
                int index = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        printLetter(index);
                    }
                }).start();
            }
        } else {
            System.out.println("передан пустой массив");
        }
    }

    private void printLetter(int index) {
        synchronized (monitor) {
            char letter = letters[index];
            char nextLetter = (index < letters.length - 1) ? letters[index + 1] : letters[0];
            try {
                for (int i = 0; i < count; i++) {
                    while (currentLetter != letter) {
                        monitor.wait();
                    }
                    System.out.println(letter);
                    currentLetter = nextLetter;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
