package com.hsgene.test;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * 使用内部类的原因：
 * 可以存在同名同参数方法、实现多继承、隐藏实现类只暴露接口
 * Created by hjc on 2016/12/9.
 */
public class InnerClassTest {

    @Test
    public void testInnerClass() {
        Student.Family family = new Student().new Family();
        family.setBreakFast("蛋饼");
    }

    @Setter
    @Getter
    private class Student extends Person implements DoThings {

        private Integer score;
        private String breakFast;

        /**
         * 代替Student继承School，已经继承了Person
         */
        @Setter
        @Getter
        protected class Family extends School {    //  内部类如果是私有的，可通过反射获取
            private String member;

            public void setBreakFast(String breakFast) {
                Student.this.breakFast = breakFast;
            }
        }

        @Override
        public void study() {
            System.out.println("学习！");
        }

    }

    @Setter
    @Getter
    private abstract class School {
        private String address;

        public void attendClass() {
            System.out.println("上课！");
        }
    }

    @Setter
    @Getter
    private abstract class Person {
        private String name;
        private String age;

        public void sleep() {
            System.out.println("睡觉");
        }
    }

    private interface DoThings {
        public void study();
    }
}
