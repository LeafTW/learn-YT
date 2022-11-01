package day28ts;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClassLoaderTest {
    @Test
    public void test1(){
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.out.println(loader);

        ClassLoader parent = loader.getParent();
        System.out.println(parent);

        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        ClassLoader loader1 = String.class.getClassLoader();
        System.out.println(loader1);
    }
    @Test
    public void test2()  {
        Properties pro = new Properties();
        try {
            FileInputStream fis = new FileInputStream("/Users/zi-wei/IdeaProjects/JIDEA/src/day28ts/jdbc.properties");
            pro.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String name = pro.getProperty("user");
        String pas = pro.getProperty("password");
        System.out.println(name + " " + pas);
    }
}
