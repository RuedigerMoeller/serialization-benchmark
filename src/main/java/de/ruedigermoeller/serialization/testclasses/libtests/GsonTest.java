package de.ruedigermoeller.serialization.testclasses.libtests;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentPrimitives;

import java.io.*;

/**
 * Created by ruedi on 29/05/15.
 */
public class GsonTest extends SerTest {

    Gson gson;

    public GsonTest(String title) {
        super(title);
        gson = new Gson();
    }

    @Override
    public void init() {

    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        resObject = gson.fromJson(new InputStreamReader(bin),cl);
    }

    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(bout));
        gson.toJson(toWrite,aClass, writer);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Gson g = new Gson();
        FrequentPrimitives src[] = { new FrequentPrimitives(1) };
        String s = g.toJson(src, FrequentPrimitives[].class);
        Object o = g.fromJson(s,FrequentPrimitives[].class);
        System.out.println(s);

        GsonTest gt = new GsonTest("POK");
        ByteArrayOutputStream bo = new ByteArrayOutputStream(8000);
        gt.writeTest( src, bo, src.getClass() );
        gt.readTest(new ByteArrayInputStream(bo.toByteArray()),src.getClass());
        System.out.println( gt.resObject );
    }
}
