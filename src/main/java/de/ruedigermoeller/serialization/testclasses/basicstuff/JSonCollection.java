package de.ruedigermoeller.serialization.testclasses.basicstuff;

import de.ruedigermoeller.serialization.testclasses.HasDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ruedi on 29/05/15.
 */
public class JSonCollection implements HasDescription, Serializable {

    HashMap map;
    ArrayList list;

    public JSonCollection() {
    }

    public JSonCollection(int dummy) {
        map = new HashMap();
        map.put("A", 123);
        map.put("B", 1123);
        map.put("C", 1223);
        map.put("D", 1233);
        map.put("E", 1234);

        list = new ArrayList();
        list.add( new FrequentPrimitives(1));
        list.add( 2);
        list.add( 3);
        list.add( 4);
        list.add( 5);
        list.add( 6);
        list.add( "END");
    }

    @Override
    public String getDescription() {
        return "a map of String=>int and a list holding a pojo, some numbers and a string";
    }
}
