package com.hsgene.test1;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * 使用内部类的原因：
 * 可以存在同名同参数方法、实现多继承、隐藏实现类只暴露接口、无需额外创建java文件
 * Created by hjc on 2016/12/9.
 */
public class InnerClassTest {

    @Test
    public void testInnerClass() {
        Student student = new Student();
        Student.Family family = student.new Family();
        family.setBreakFast("蛋饼");
        System.out.println(student.getBreakFast());
        System.out.println(new InnerClassTest.Teacher().getCourse());
    }

    @Setter
    @Getter
    private class Student extends Person implements DoThings {

        private Integer score;
        private String breakFast;

        /**
         * 代替Student继承School，Student已继承Person
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

    /**
     * 静态内部类一般只用于所在的外部类，可以直接调用，不依赖所在外部类
     */
    @Setter
    @Getter
    static class Teacher{
        private String course;
    }
}
