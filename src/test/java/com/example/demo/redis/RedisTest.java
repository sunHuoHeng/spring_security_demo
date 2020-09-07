package com.example.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("key","value");
        print();
        Object key = ops.get("key");
        System.out.println(key);
        endprint();
    }

    public static void print(){
        System.out.println("       _");
        System.out.println("      | |");
        System.out.println("      | |");
        System.out.println("__    | |    __");
        System.out.println("\\ \\   | |   / /");
        System.out.println(" \\ \\  | |  / /");
        System.out.println("  \\ \\ | | / /");
        System.out.println("   \\ \\| |/ /");
        System.out.println("    \\ \\ / /");
        System.out.println("     \\   /");
        System.out.println("      \\_/");
    }

    public static void endprint(){

        System.out.println("       _");
        System.out.println("      / \\");
        System.out.println("     /   \\");
        System.out.println("    /     \\");
        System.out.println("   / /| |\\ \\");
        System.out.println("  / / | | \\ \\");
        System.out.println(" / /  | |  \\ \\");
        System.out.println("/_/   | |   \\_\\");
        System.out.println("      | |");
        System.out.println("      | |");
        System.out.println("      |_|");

    }
}
