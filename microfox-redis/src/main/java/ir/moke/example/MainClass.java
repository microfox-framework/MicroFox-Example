package ir.moke.example;

import ir.moke.microfox.redis.RedisFactory;

import static ir.moke.microfox.MicroFox.redis;
public class MainClass {
    static {
        initialize();
    }

    public static void main(String[] args) {
        redis("my-redis",redis -> {
            redis.set("a1","Hello");
            redis.set("a2","Mahdi");
        });

        redis("my-redis",redis -> {
            System.out.println(redis.get("a1"));
            System.out.println(redis.get("a2"));
        });
    }

    private static void initialize() {
        RedisFactory.register("my-redis","127.0.0.1",6379,false);
    }
}
