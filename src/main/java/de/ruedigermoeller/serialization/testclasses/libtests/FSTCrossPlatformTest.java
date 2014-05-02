package de.ruedigermoeller.serialization.testclasses.libtests;

import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * Created by ruedi on 02.05.14.
 */
public class FSTCrossPlatformTest extends FSTTest {
    public FSTCrossPlatformTest(String desc, boolean uns, boolean preferSpeed) {
        super(desc, uns, preferSpeed);
    }

    @Override
    public void init() {
        defconf = FSTConfiguration.createCrossPlatformConfiguration();
        defconf.setPreferSpeed(preferSpeed);
        in = new FSTObjectInput(defconf);
        out = new FSTObjectOutput(defconf);
    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        super.readTest(bin,cl);
    }
    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        super.writeTest(toWrite,bout,aClass);

    }

}
