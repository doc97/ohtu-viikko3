package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/riissdan/Software/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        login(driver);
        sleep(3);
        failLoginWrongPassword(driver);
        sleep(3);
        failLoginWrongUsername(driver);
        sleep(3);
        createNewUser(driver);
        sleep(3);
        logoutFromWelcome(driver);
        sleep(3);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void login(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
    }
    
    private static void failLoginWrongPassword(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
    }
    
    private static void failLoginWrongUsername(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
    }
    
    private static void createNewUser(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random random = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + random.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
    }
    
    private static void logoutFromWelcome(WebDriver driver) {
        WebElement element = driver.findElement(By.partialLinkText("continue"));
        element.click();

        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
