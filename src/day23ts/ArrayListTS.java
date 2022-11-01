package day23ts;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ArrayListTS {

    @Test
    public void test1(){
        ArrayList list=new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new person("Ton",22));
        list.add(6666);

        System.out.println(list);

        List<Integer> list1 = Arrays.asList(123, 456);
        list.addAll(list1);
        System.out.println(list);

        int i = list.indexOf(4567);
        System.out.println(i);

        list.remove(1);
        System.out.println(list);

        list.set(1,9999);
        System.out.println(list);

        List list2 = list.subList(1, 4);
        System.out.println(list2);

//        System.out.println(Collections.max(list));

    }
}

