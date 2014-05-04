package de.ruedigermoeller.serialization.testclasses.libtests;

import org.boon.json.JsonFactory;
import org.boon.json.JsonSerializer;
import org.boon.json.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * Created by ruedi on 04.05.14.
 */
public class BoonTest extends SerTest {
    ObjectMapper mapper;

    public BoonTest(String title) {
        super(title);
    }

    @Override
    public void init() {
        mapper = JsonFactory.create();
    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        resObject = mapper.fromJson(bin,cl);
    }

    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        mapper.writeValue(bout, toWrite);
    }
}
