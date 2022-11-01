package day29ts;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;

public class LambadaTS {
    @Test
    public void test1(){
        Consumer<String> con = s -> System.out.println(s);
        con.accept("Consumer");

        Supplier<String> sup= ()->new String("supplier");
        System.out.println(sup.get());

        Function<Integer,String > fun=i1->new String("ans: "+i1);
        System.out.println(fun.apply(888));

        Predicate<String> ped=s -> s.contains("葉");
        System.out.println(ped.test("葉維"));
    }

    @Test
    public void test2(){
        Comparator<Integer> com1=(i1, i2)->Integer.compare(i1,i2);
        Comparator<Integer> com2=Integer::compare;
        System.out.println(com2.compare(12, 13));
        BiPredicate<String,String> bif=String::equals;
        System.out.println(bif.test("abc", "abd"));
    }
}
