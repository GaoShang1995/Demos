package com.gaoshang.reflection01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author GaoShang
 * @Date 2020-09-24 15:39
 * @Email gs_nuaa@163.com
 */
public class Reflection01 {

    /**
     * 知识点：
     * Reflection是被视为动态语言的关键，在运行期间可以获得类的内部信息，并通过API操作类
     * <p>
     * 附加需要学习的知识点：类的加载过程
     */

    @Test
    public void reflectionTest01() throws Exception {

        //可以通过反射获取构造器
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        Object personObj = constructor.newInstance("张三", 15);
        Person person = (Person) personObj;
        System.out.println(person.toString());

        //可以通过反射获取类的指定属性
        Field field = personClass.getDeclaredField("name");
        field.setAccessible(true);//私有属性可设置访问权限
        field.set(person, "李四");
        System.out.println(person.toString());

        //可以通过反射获取类的指定方法
        Method method = personClass.getDeclaredMethod("introduce");
        method.setAccessible(true);
        method.invoke(person);

        //获取私有构造器
        Constructor privateConstructor = personClass.getDeclaredConstructor(String.class);
        privateConstructor.setAccessible(true);
        Object privatePersonObj = privateConstructor.newInstance("王五");
        System.out.println(privatePersonObj.toString());
    }

    /**
     * @description: 获取Class对象的方法
     * @author: Gaoshang
     * @date:2020_09_24
     * @email:gs_nuaa@163.com
     */
    @Test
    public void getClassObj() throws ClassNotFoundException {

        /*方法1：调用运行时类的属性class*/
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        /*方法2：调用运行时类的对象的方法*/
        Person person = new Person();
        Class personClass1 = person.getClass();
        System.out.println(personClass1);

        /*方法3：调用Class类的静态方法：forName(String classPath),需要捕获异常*/
        Class personClass2 = Class.forName("com.gaoshang.reflection01.Person");
        System.out.println(personClass2);

        /*方法4：使用类加载器*/
        ClassLoader classLoader = Reflection01.class.getClassLoader();
        Class personClass3 = classLoader.loadClass("com.gaoshang.reflection01.Person");
        System.out.println(personClass3);

    }


    /*
     * @description: 通过类加载器加载配置文件
     * @author Gaoshang
     * @date 2020/10/9
     * @email gs_nuaa@163.com
     */
    @Test
    public void getPropertiesByClassLoader() throws IOException {
        ClassLoader classLoader = Reflection01.class.getClassLoader();
        System.out.println(classLoader);
        InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        System.out.println(properties.getProperty("test"));
    }

    @Test
    public void getRuntimeClassInstance() throws IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;
        Object object = personClass.newInstance();
        System.out.println(object);
    }

    @Test
    public void treeMapSort(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String) o1;
                String s2 = (String) o2;
                return s1.compareTo(s2);
            }
        });
        map.put("1",1);
        map.put("3",2);
        map.put("5",3);
        map.put("4",4);

        Set entrySet = map.entrySet();
        Iterator iterator=entrySet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}



/**
 * @description: Person Class
 * @author: Gaoshang
 * @date:2020_09_24
 * @email:gs_nuaa@163.com
 */
class Person {
    private String name;
    private int age;

    /*Constructors*/
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    /*get和set*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*toString*/
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /*my methods*/
    public void greeting() {
        System.out.println("Hi,My name is Shawn.Good to see you!");
    }

    private void introduce() {
        System.out.println("I am a Java Engineer");
    }
}
