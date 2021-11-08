package com.bobocode;


import com.bobocode.service.TrimmedService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StringTrimmingApplication {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("com.bobocode.service", "com.bobocode.config");
        var trimmedService = context.getBean(TrimmedService.class);
        var var1 = "     hello";
        var var2 = "     hello           ";
        trimmedService.testStringParameter(var1, var2);
        var returnString = trimmedService.testStringParameter(var1);
        System.out.println("2. return length " + returnString.length());
        trimmedService.testIntegerParameter(1, var1);
        trimmedService.testIntegerReturnType(var1);
    }
}
