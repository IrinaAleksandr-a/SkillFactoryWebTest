package pages;

import lib.CorePageCase;
import lib.DirectionsСonstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DirectionPage extends CorePageCase {

    private final static String IMAGE_COURSES = "//*[@data-elem-type='image']";
    private final String nameDirection;

    public DirectionPage(WebDriver webDriver, String nameDirection) {
        super(webDriver);
        this.nameDirection = nameDirection;
    }

    public int getCountCourses() {
        return getCourses().size();
    }

    private List<WebElement> getCourses() {
        List<WebElement> courses = new ArrayList<>();
        String[] containers = DirectionsСonstant.getContainer(nameDirection);
        for (String container : containers) {
            courses.addAll(waitForElementsPresent(By.xpath(container + IMAGE_COURSES),
                    "Cannot find 'Image Courses'", 5));
        }
        return courses;
    }
}
