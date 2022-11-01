package day23ts;

import org.junit.jupiter.api.Test;

import java.util.*;

public class HashMapts {
    @Test
    public void test1(){
        Map map=new HashMap();
        map.put("AA",123);
        map.put(45,666);
        map.put("BB",123);
        System.out.println(map);

        Map map1=new HashMap();
        map.put("DD",123);
        map.put("BB",456);
        map.put(33,7777);

        map.putAll(map1);
        System.out.println(map);

        map.remove("DD");

        map.clear();
        System.out.println(map.isEmpty());

    }

    @Test
    public void test2(){
        Map map=new HashMap();
        map.put("AA",123);
        map.put(45,666);
        map.put("BB",123);

        System.out.println(map.get("AA"));
        System.out.println(map.containsKey(45));
        System.out.println(map.containsValue(124));
    }

    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 666);
        map.put("BB", 1234);

        Set set1 = map.keySet();
        Set set = set1;
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        Collection values = map.values();
        for (Object obj :values) {
            System.out.println(obj);
        }
        System.out.println();
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println();
        Set set2 = map.keySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key +" "+value);
        }
    }
}
