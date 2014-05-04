package de.ruedigermoeller.serialization.testclasses.libtests;

import data.media.Image;
import data.media.Media;
import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;
import de.ruedigermoeller.serialization.minbin.MBPrinter;
import de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentPrimitives;
import de.ruedigermoeller.serialization.testclasses.enterprise.ObjectOrientedDataType;
import de.ruedigermoeller.serialization.testclasses.enterprise.ObjectOrientedInt;
import de.ruedigermoeller.serialization.testclasses.enterprise.SimpleOrder;
import de.ruedigermoeller.serialization.testclasses.enterprise.Trader;

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

        defconf.registerCrossPlatformClassMapping(
                new String[][] {
                        {"prim", FrequentPrimitives.class.getName()},
                        {"prim[]", FrequentPrimitives[].class.getName()},

                        {"trader", Trader.class.getName() },
                        {"order", SimpleOrder.class.getName() },
                        {"ooint", ObjectOrientedInt.class.getName() },
                        {"oodata", ObjectOrientedDataType.class.getName() },

                        {"media", Media.class.getName() },
                        {"mediaplayer", Media.Player.class.getName() },
                        {"imgsize", Image.Size.class.getName() },
                        {"img", Image.class.getName() },

                }
        );

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

    @Override
    public void run(Object toWrite, int warmupTime, int testTimeMS) {
        super.run(toWrite, warmupTime, testTimeMS);
//        try {
//            MBPrinter.printMessage(defconf.asByteArray((java.io.Serializable) toWrite), System.out);
//        } catch (Throwable ex) {
//            System.out.println("could not print:"+ex);
//        }
    }
}
