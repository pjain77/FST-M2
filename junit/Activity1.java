package Activities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {

     static ArrayList<String> list;

    @BeforeAll
      static void setUp(){

            list= new ArrayList<String>();
            list.add("alpha");
            System.out.println("Executing setup method");
            list.add("beta");
    }

    @Test
    public void insertTest(){
        assertEquals(2,list.size(),"Wrong size");
        list.add("charlie");
        assertEquals(3,list.size());
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("beta", list.get(1), "Wrong element");
        assertEquals("charlie", list.get(2), "Wrong element");
    }

    /*@Test
    public void replaceTest(){

    }*/
}
