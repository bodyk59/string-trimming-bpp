package com.bobocode.service;

import com.bobocode.trimming.anotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class TrimmedService {

    public void testStringParameter(String s1, String s2) {
        System.out.println("1. parameter length " + s1.length());
        System.out.println("1. parameter length " + s2.length());
    }

    public String testStringParameter(String s1) {
        System.out.println("2. parameter length " + s1.length());
        return "   Hello   ";
    }

    public void testIntegerParameter(Integer i1, String s1) {
        System.out.println("3. parameter length " + s1.length());
    }

    public Integer testIntegerReturnType(String s1) {
        System.out.println("4. parameter length " + s1.length());
        return 1;
    }
}
