package br.tatuapu.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author tatuapu
 */
public class Base {
    
    public static boolean existsElement(WebDriver driver, By b) {
        try {
            driver.findElement(b);
        }catch (NoSuchElementException error) {
            return false;
        }
        return true;
    }
    public static boolean existsElement(WebElement e, By b) {
        try {
            e.findElement(b);
        }catch (NoSuchElementException error) {
            return false;
        }
        return true;
    }
    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'a' - 1)) : null;
    }
    public static String toAlphabetic(int i) {
        if( i<0 ) {
            return "-"+toAlphabetic(-i-1);
        }

        int quot = i/26;
        int rem = i%26;
        char letter = (char)((int)'A' + rem);
        if( quot == 0 ) {
            return ""+letter;
        } else {
            return toAlphabetic(quot-1) + letter;
        }
    }
}
