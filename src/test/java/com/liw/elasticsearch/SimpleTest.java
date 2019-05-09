package com.liw.elasticsearch;

import com.liw.easyrules.EasyRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
public class SimpleTest {
    @Test
    public void  testDemo() throws FileNotFoundException {
        new EasyRules().demo();
    }
}
