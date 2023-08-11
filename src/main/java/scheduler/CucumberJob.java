package scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import runner.RunTest;

public class CucumberJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        RunTest runTest = new RunTest();
        runTest.runCucumber();
    }
}
