import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SILab2Test {

    @Test
    void testEveryStatement() {
        List<Item> items = List.of(new Item("apple", 2, 100, 0));
        assertEquals(200.0, SILab2.checkCart(items, "1234567812345678"));

        List<Item> discountItems = List.of(new Item("banana", 2, 350, 0.2));
        assertEquals(350 * (1 - 0.2) * 2 - 30, SILab2.checkCart(discountItems, "1234567812345678"));

        List<Item> valid = List.of(new Item("orange", 2, 100, 0));
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(valid, "1234abcd56789012")
        );
        assertTrue(ex.getMessage().contains("Invalid character"));
    }

    @Test
    void testMultipleCondition() {
        assertFalse(triggersPenalty(100, 0, 2));   // FFF
        assertTrue(triggersPenalty(400, 0, 2));    // TFF
        assertTrue(triggersPenalty(100, 0.1, 2));  // FTF
        assertTrue(triggersPenalty(100, 0, 20));   // FFT
        assertTrue(triggersPenalty(400, 0.1, 2));  // TTF
        assertTrue(triggersPenalty(400, 0, 20));   // TFT
        assertTrue(triggersPenalty(100, 0.1, 20)); // FTT
        assertTrue(triggersPenalty(400, 0.1, 20)); // TTT
    }

    private boolean triggersPenalty(double price, double discount, int quantity) {
        Item i = new Item("test", (int) quantity, (int) price, discount);
        return i.getPrice() > 300 || i.getDiscount() > 0 || i.getQuantity() > 10;
    }
}
