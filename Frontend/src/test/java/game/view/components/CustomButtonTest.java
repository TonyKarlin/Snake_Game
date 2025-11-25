/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package game.view.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author tontt
 */
public class CustomButtonTest {

    public CustomButtonTest() {
    }

    /**
     * Test of initializeButton method, of class CustomButton.
     */
    @Test
    public void testInitializeButton() {
        System.out.println("initializeButton");
        String name = "Test";
        CustomButton instance = new CustomButtonImpl();
        HBox expResult = null;
        HBox result = instance.initializeButton(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getButtonComponent method, of class CustomButton.
     */
    @Test
    public void testGetButtonComponent() {
        System.out.println("getButtonComponent");
        CustomButton instance = new CustomButtonImpl();
        HBox expResult = null;
        HBox result = instance.getButtonComponent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultButtonPadding method, of class CustomButton.
     */
    @Test
    public void testGetDefaultButtonPadding() {
        System.out.println("getDefaultButtonPadding");
        CustomButton instance = new CustomButtonImpl();
        int expResult = 0;
        int result = instance.getDefaultButtonPadding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultPosition method, of class CustomButton.
     */
    @Test
    public void testGetDefaultPosition() {
        System.out.println("getDefaultPosition");
        CustomButton instance = new CustomButtonImpl();
        Pos expResult = null;
        Pos result = instance.getDefaultPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setButtonPadding method, of class CustomButton.
     */
    @Test
    public void testSetButtonPadding() {
        System.out.println("setButtonPadding");
        int newPadding = 0;
        CustomButton instance = new CustomButtonImpl();
        instance.setButtonPadding(newPadding);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onClick method, of class CustomButton.
     */
    @Test
    public void testOnClick() {
        System.out.println("onClick");
        CustomButton instance = new CustomButtonImpl();
        instance.onClick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CustomButtonImpl extends CustomButton {

        @Override
        public void onClick() {

        }
    }

}