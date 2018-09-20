package jvsmoke.com;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TextFieldComTest {

    @Test
    void getValue() {
        Rectangle bounds = new Rectangle(1, 10, 100, 25);
        TextFieldCom tfc = ComFactory.TextFieldCom(bounds, "", true, true);
        tfc.setText("35");
        int expectInt = 35;
        int actualInt = (int) tfc.getValue();
        assertEquals(expectInt, actualInt);
        tfc.setText("35.5");
        double expectDouble = 35.5;
        double actualDouble = (double) tfc.getValue();
        assertEquals(expectDouble, actualDouble);
    }
}