package com.ren;

import com.ren.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WmsApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Result.suc(true));
    }

}
