package de.ruedigermoeller.serialization.testclasses.libtests;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ruedi on 04.05.14.
 */
public class JSonIOTest extends SerTest {

    public JSonIOTest(String title) {
        super(title);
    }

    @Override
    public void init() {

    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        JsonReader reader = new JsonReader(bin);
        try {
            resObject = reader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        try {
            JsonWriter writer = new JsonWriter(bout);
            writer.write(toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
