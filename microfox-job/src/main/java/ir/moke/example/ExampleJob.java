package ir.moke.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ExampleJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        logger.info("Job successfully executed");
    }
}
