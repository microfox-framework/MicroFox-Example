package ir.moke.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ir.moke.microfox.MicroFox.job;

public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        logger.info("microfox sample job started");
        job(() -> logger.info("Hello"), "*/3 * * * * ? *");
    }
}
