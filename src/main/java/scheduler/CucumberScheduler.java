package scheduler;

import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.JobDetail;
import org.quartz.JobBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.impl.StdSchedulerFactory;
import java.util.TimeZone;

public class CucumberScheduler {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(CucumberJob.class)
                .withIdentity("cucumberJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cucumberTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 06 14 * * ?").inTimeZone(TimeZone.getTimeZone("Asia/Jakarta"))) // Set cron schedule (every day at)
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}

