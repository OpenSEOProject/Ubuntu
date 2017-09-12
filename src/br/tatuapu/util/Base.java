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
}
