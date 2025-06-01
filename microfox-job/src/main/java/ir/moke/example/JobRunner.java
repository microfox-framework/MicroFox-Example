package ir.moke.example;

import static ir.moke.microfox.MicroFox.job;

public class JobRunner {

    public static void main(String[] args) {
        job(ExampleJob.class,"*/3 * * * * ? *");
    }
}
