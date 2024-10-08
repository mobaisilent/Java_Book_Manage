package com.test;

import org.junit.Test;
import org.manage.sql.SqlUtil;

public class Main {
    @Test
    public void test1() {
        SqlUtil.doSqlWork(mapper -> {
            mapper.getAllBorrows().forEach(System.out::println);
        });
    }
}
