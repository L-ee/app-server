package test;

import model.Person;
//import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/6/6 19:01
 */
public class Java8Test {

    //@Test
    public void test(){


        List<Person> list = new ArrayList<Person>();
        for(int i = 0; i<10 ; i++){

            Person p = new Person();
            p.setId(i+2);
            p.setType(i*3%2 == 0 ? 0:1);
            p.setName("Market:" + i);
            list.add(p);


        }


        List<Person> collect = list.stream()
                .filter(t -> t.getType() == 1)
                .sorted(Comparator.comparing(Person::getId).reversed())
                        //.map(Person::getId)
                .collect(Collectors.toList());
        //System.out.println(count);

        for (Person i : collect){
            System.out.println(i);
        }


    }


}
