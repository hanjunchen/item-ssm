package com.hsgene.test2;

import com.alibaba.fastjson.JSON;
import com.hsgene.entity.Employee;
import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * Created by hjc on 2016/12/13.
 */
public class WeakReferenceTest {

    /**
     * 使用场景：很容易被重新构建, 且很消耗内存的对象，主对象依赖于次对象，主对象一直被使用，但是用不到次对象，次对象的弱引用会被回收
     */
    @Test
    public void testWeakReference() {
        //  WeakReference弱引用和GC的关系，当弱引用weakReference指向一个强引用对象时，除非强引用对象被正在使用，否则即使弱引用被正在使用，GC仍然会回收该对象
        Employee employee = new Employee() {{
            setName("sb");
        }};
        WeakReference<Employee> weakReference = new WeakReference(employee);
        //  weakReference和employee是不同引用指向同一个对象，一个是弱引用一个是强引用，
        // 只有强引用一直被使用中时，实际对象才不会被GC回收，弱引用无论是否被使用都不影响GC回收对象，对象一旦被回收引用置为null
        System.out.println(JSON.toJSONString(weakReference.get()));
        int i = 0;
        while (true) {
            if (weakReference.get() != null) {
                i++;
                System.out.println("弱引用对象循环 " + i + " 次：" + weakReference); //  对象的弱引用虽然被使用但仍会被回收
            } else {
                System.out.println("对象被GC回收！");
                break;
            }
        }
        /*int j = 0;
        while (true) {
            System.out.println("强引用对象循环 " + j + " 次：" + employee);  //  对象的强引用一直被使用，对象始终不会被回收
            if (weakReference.get() != null) {
                j++;
                System.out.println("弱引用对象循环 " + j + " 次：" + weakReference.get()); //  因为对象强引用一直被使用，对象不会被回收，所以弱引用也一直不为null
            } else {
                System.out.println("对象被GC回收！");
                break;
            }
        }*/
    }
}
