package com.example.playground.model;

import java.util.ArrayList;
import java.util.List;

public class JsonRequest {
    private String testField = "";
    private TestClass testClass = new TestClass();
    private List<String> testList = new ArrayList<>();

    public static class TestClass {
        private String classVal1 = "";
        private String classVal2 = "";
        private String classVal3 = "";

        public String getClassVal1() {
            return classVal1;
        }

        public void setClassVal1(String classVal1) {
            this.classVal1 = classVal1;
        }

        public String getClassVal2() {
            return classVal2;
        }

        public void setClassVal2(String classVal2) {
            this.classVal2 = classVal2;
        }

        public String getClassVal3() {
            return classVal3;
        }

        public void setClassVal3(String classVal3) {
            this.classVal3 = classVal3;
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    "classVal1='" + classVal1 + '\'' +
                    ", classVal2='" + classVal2 + '\'' +
                    ", classVal3='" + classVal3 + '\'' +
                    '}';
        }
    }

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    @Override
    public String toString() {
        return "JsonRequest{" +
                "testField='" + testField + '\'' +
                ", testClass=" + testClass +
                ", testList=" + testList +
                '}';
    }
}
