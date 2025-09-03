package Selenium_Framework.utils;

import static Selenium_Framework.utils.ElementUtils.getElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * A utility class for sending keyboard actions to elements and the browser.
 * It simplifies common keyboard operations like sending text, key combinations,
 * and simulating system-level keystrokes for tasks like authentication.
 */
public class KeyboardUtils {
    
    /**
     * Sends a string of text to a specified input field.
     *
     * @param locator The {@link By} locator of the element.
     * @param text The text to enter into the element.
     */
    public static void sendKeys(By locator, String text) {
        getElement(locator).sendKeys(text);
    }
    
    /**
     * Sends a single keyboard key to the specified {@link WebElement}.
     *
     * @param element The target {@link WebElement}.
     * @param key The {@link Keys} enum representing the key to send.
     */
    public static void sendKey(WebElement element, Keys key) {
        element.sendKeys(key);
    }

    /**
     * Sends a single keyboard key to an element located by a `By` locator.
     *
     * @param locator The {@link By} locator of the element.
     * @param key The {@link Keys} enum representing the key to send.
     */
    public static void sendKey(By locator, Keys key) {
        getElement(locator).sendKeys(key);
    }

    /**
     * Sends a combination of keys (a chord) to the specified element.
     * This is useful for shortcuts like Ctrl+A, Ctrl+C, etc.
     *
     * @param element The target {@link WebElement}.
     * @param key1 The first key in the combination.
     * @param key2 The second key in the combination.
     */
    public static void sendKeyCombination(WebElement element, Keys key1, Keys key2) {
        element.sendKeys(Keys.chord(key1, key2));
    }
    
    /**
     * Sends the ENTER key to the specified element.
     *
     * @param element The target {@link WebElement}.
     */
    public static void sendEnter(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Sends the TAB key to the specified element.
     *
     * @param element The target {@link WebElement}.
     */
    public static void sendTab(WebElement element) {
        element.sendKeys(Keys.TAB);
    }

    /**
     * Selects all text within an element by sending the Ctrl + A key combination.
     *
     * @param element The target {@link WebElement}.
     */
    public static void copyAll(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    }

    /**
     * Copies selected text from an element by sending the Ctrl + C key combination.
     *
     * @param element The target {@link WebElement}.
     */
    public static void copyText(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "c"));
    }

    /**
     * Pastes text into the specified element by sending the Ctrl + V key combination.
     *
     * @param element The target {@link WebElement}.
     */
    public static void pasteText(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    }
    
    /**
     * Simulates a user entering a username and password into a basic authentication popup
     * using the `java.awt.Robot` class. This is a workaround for popups that Selenium
     * cannot interact with directly.
     *
     * @param name The username to type.
     * @param pass The password to type.
     * @throws AWTException if the `Robot` class cannot be instantiated due to platform issues.
     */
    public static void basicAuthUsingRobot(String name, String pass) throws AWTException {
        Robot robot = new Robot();
        WaitUtils.setImplicitWait(1); // Small wait to allow the popup to appear.

        // Type username
        for (char c : name.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

        // Press TAB to move to the password field
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        // Type password
        for (char c : pass.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

        // Press ENTER to submit the credentials
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}