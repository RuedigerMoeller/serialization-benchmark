package de.ruedigermoeller.serialization.testclasses.libtests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ruedi on 04.05.14.
 */
public class JacksonTest extends SerTest {
    ObjectMapper mapper;

    public JacksonTest(String title) {
        super(title);
    }

    @Override
    public void init() {
        mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        try {
            resObject = mapper.readValue(bin, cl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        try {
            mapper.writeValue(bout, toWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
