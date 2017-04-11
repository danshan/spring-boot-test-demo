package com.shanhh.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.RandomUtils;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author dan
 * @since 2017-04-11 09:20
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected String mockStr(int length) {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, Math.min(32, length));
    }

    protected int mockInt() {
        return this.mockInt(10000);
    }

    protected int mockInt(int max) {
        return this.mockInt(0, max);
    }

    protected int mockInt(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    protected long mockLong() {
        return this.mockLong(10000);
    }

    protected long mockLong(long max) {
        return this.mockLong(0, max);
    }

    protected long mockLong(long min, long max) {
        return RandomUtils.nextLong(min, max);
    }

    protected short mockShort() {
        return this.mockShort(100);
    }

    protected short mockShort(int max) {
        return this.mockShort(0, max);
    }

    protected short mockShort(int min, int max) {
        return new Integer(RandomUtils.nextInt(min, max)).shortValue();
    }

    protected BigDecimal mockMoney(int maxYuan) {
        return new BigDecimal(mockInt(maxYuan - 1) + "." + mockInt(99));
    }

    protected String mockEmail() {
        return mockStr(10) + "@mock.com";
    }

    protected String mockMobile() {
        return "186" + mockLong(10000000, 99999999);
    }

}
