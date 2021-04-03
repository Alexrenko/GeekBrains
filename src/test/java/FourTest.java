import Java_3_Lesson_6.Testing.Four;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FourTest {

   @Test
    public void test_getAllAfterFour_One() {
       int[] inputArray = {3, 2, 7, 8, 4, 3, 1};
       int[] result = {3, 1};
       Assertions.assertArrayEquals(result, Four.getAllAfterFour(inputArray));
   }

    @Test
    public void test_getAllAfterFour_Two() {
        int[] inputArray = {3, 7, 9, 4, 1, 8, 7};
        int[] result = {1, 8, 7};
        Assertions.assertArrayEquals(result, Four.getAllAfterFour(inputArray));
    }

    @Test
    public void test_getAllAfterFour_Ex() {
        int[] inputArray = {3, 2, 7};
        Assertions.assertThrows(RuntimeException.class, () -> Four.getAllAfterFour(inputArray));
    }

    @Test
    public void test_checkOneAndFour_positive_One() {
        int[] inputArray = {1, 3, 2, 5, 4, 0};
        Assertions.assertTrue(Four.checkOneAndFour(inputArray));
    }

    @Test
    public void test_checkOneAndFour_positive_Two() {
        int[] inputArray = {1, 4, 1, 4, 1, 4};
        Assertions.assertTrue(Four.checkOneAndFour(inputArray));
    }

    @Test
    public void test_checkOneAndFour_negative_One() {
        int[] inputArray = {7, 3, 2, 5, -2, 0};
        Assertions.assertFalse(Four.checkOneAndFour(inputArray));
    }

    @Test
    public void test_checkOneAndFour_negative_Two() {
        int[] inputArray = {};
        Assertions.assertFalse(Four.checkOneAndFour(inputArray));
    }
}
