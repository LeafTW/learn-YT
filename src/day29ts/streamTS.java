package day29ts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTS {
    @Test
    public void test() {
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> listS = list.stream();
        listS.filter(m->m.getAge()>30).sorted((e1,e2)->-Integer.compare(e1.getAge(),e2.getAge())).
                forEach(System.out::println);
    }
    @Test
    public void test1(){
        List<String> arlist = Arrays.asList("aa", "bb", "cc", "dd");
        arlist.stream().map(str->str.toUpperCase()).forEach(System.out::println);

        List<Employee> list = EmployeeData.getEmployees();
        Stream<String> nameStream = list.stream().map(Employee::getName);
        nameStream.filter(d->d.length()>3).forEach(System.out::println);

    }

    @Test
    public void test2(){
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();
        //filter
        stream.filter(d->d.getName().length()>3).forEach(System.out::println);
        //limit
        list.stream().limit(3).forEach(System.out::println);
        //skip
        list.stream().skip(3).forEach(System.out::println);
        //distinct 篩選重複
        list.add((new Employee(1006, "比尔盖茨", 42, 9500.43)));
        list.add((new Employee(1006, "比尔盖茨", 42, 9500.43)));
        list.add((new Employee(1006, "比尔盖", 42, 9500.43)));
        list.add((new Employee(1007, "比尔盖茨", 42, 9500.43)));
        list.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<Employee> list = EmployeeData.getEmployees();
        //檢查是否批配所有元素
        System.out.println(list.stream().allMatch(d -> d.getAge() > 18));
        //檢查是否批配一元素
        System.out.println(list.stream().anyMatch(d -> d.getSalary() > 1000));
        //檢查是否沒有批配一元素
        System.out.println(list.stream().noneMatch(d -> d.getName().startsWith("雷")));
        //findfirst一返回第一个元素
        System.out.println(list.stream().findFirst());
        //findAny一返回当前流中的任意元素
        System.out.println(list.stream().findAny());
        System.out.println("\n********************\n");
        //count-返回流中元素的总个数
        System.out.println(list.stream().filter(s->s.getSalary()>5500).count());
        //max(Comparator c)一返回流中最大值
        System.out.println("Ex1: "+list.stream().max((i1, i2) -> Double.compare(i1.getId(), i2.getId())));
        System.out.println("Ex2: "+list.stream().map(d -> d.getSalary()).max(Double::compare));
        //练习:返回最高的工资:
        //min(Comparatorc)一返回流中最小值

        //练习:返回最低工资的员工
        //foreach(Consumer c)一内部送代
    }

    @Test
    public void test4(){
        List<Integer> as = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(as.stream().reduce(0, Integer::sum));
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Double> st = list.stream().map(Employee::getSalary);
        Optional<Double> reduce = st.reduce(Double::sum);
        System.out.println("Ex1: "+reduce);
        Stream<Double> st1 = list.stream().map(Employee::getSalary);
        Optional<Double> reduce1 = st1.reduce((s1,s2)->s1+s2);
        System.out.println("Ex2: "+reduce1);
    }

    @Test
    public void test5(){
        List<Employee> list = EmployeeData.getEmployees();
        List<Employee> tolist = list.stream().filter(d -> d.getSalary() > 5500).collect(Collectors.toList());
        System.out.println(tolist);

        Set<Employee> toset = list.stream().filter(d -> d.getAge() > 20).collect(Collectors.toSet());
        System.out.println(toset);

    }
}
