package ir.moke.example;

import ir.moke.microfox.redis.RedisFactory;
import redis.clients.jedis.Jedis;

import static ir.moke.microfox.MicroFox.*;
public class MainClass {
    static {
        initialize();
    }

    public static void main(String[] args) {
        redis("my-redis",redis -> {
            Jedis jedis = (Jedis) redis;
            jedis.set("a1","Hello");
            jedis.set("a2","Mahdi");
        });

        redis("my-redis",redis -> {
            Jedis jedis = (Jedis) redis;
            System.out.println(jedis.get("a1"));
            System.out.println(jedis.get("a2"));
        });
    }

    private static void initialize() {
        RedisFactory.register("my-redis","127.0.0.1",6379,false);
    }
}
