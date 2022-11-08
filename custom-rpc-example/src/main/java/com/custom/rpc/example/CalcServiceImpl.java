package com.custom.rpc.example;

/**
 * @ClassName CalcServiceImpl
 * @Description TODO
 * @Author peco
 * @Date 2022/11/8 14:48
 */
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
