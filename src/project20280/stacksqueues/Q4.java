package project20280.stacksqueues;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Q4 {
    static String convertToBinary(long dec_num) {
        LinkedStack<Long> st = new LinkedStack<>();
        String output = "";

        while (dec_num > 0) {
            st.push(dec_num % 2);
            dec_num /= 2;
        }

        while (!st.isEmpty()) {
            output += st.pop();
        }

        return output;
    }

    @Test
    public void testConvertToBinary() {
        assertEquals("10111", convertToBinary(23));
        assertEquals("111001000000101011000010011101010110110001100010000000000000",
                convertToBinary(1027010000000000000L));
    }
}