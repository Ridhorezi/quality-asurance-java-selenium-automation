package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import job.EmailSender;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDef",
        tags = "@TDD or @Login or @Register",
        plugin = {"pretty", "html:target/RegressionReport.html"}
)

public class RunTest {
    public void runCucumber() {
        // Running testing
        Result result = JUnitCore.runClasses(getClass());;

        // Error handling
        if (result.wasSuccessful()) {
            System.out.println("Test successfully running");
        } else {
            System.out.println("Ups! something when wrong");
            for (Failure failure : result.getFailures()) {
                System.out.println("Failure: " + failure.toString());
            }
        }

        // Send email with the HTML report attached
        String recipientEmail = "your_email@example.com";
        String subject = "Cucumber Test Report";
        String body = "Hi,\nPlease find the attached Cucumber test report.";
        String attachmentPath = "D:\\qa-project\\qa-automation-java\\qa-automation-java-selenium\\target\\RegressionReport.html";

        EmailSender.sendEmail(recipientEmail, subject, body, attachmentPath);
    }
}
