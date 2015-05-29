package de.ruedigermoeller.serialization.testclasses.libtests;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.nustaq.serialization.util.FSTUtil;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * Created by ruedi on 23/04/15.
 */
public class FST20Test extends SerTest {

    FSTConfiguration defconf;
    boolean uns;
    boolean unshared;
    FSTObjectInput in;
    FSTObjectOutput out;

    public FST20Test(String desc,boolean unsafe,boolean unshared) {
        super(desc);
        this.uns = unsafe;
        this.unshared = unshared;
    }

    @Override
    public void init() {
        if ( uns )
            defconf = FSTConfiguration.createFastBinaryConfiguration();
        else
            defconf = FSTConfiguration.createDefaultConfiguration();
        defconf.setShareReferences(!unshared);
        in = new FSTObjectInput(defconf);
        out = new FSTObjectOutput(defconf);
    }

    public String getColor() {
        return "#4040a0";
    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        try {
            in.resetForReuse(bin);
            Object res = in.readObject(cl);
            resObject = res;
        } catch (Throwable e) {
            FSTUtil.printEx(e);
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        out.resetForReUse(bout);
        try {
            out.writeObject(toWrite, aClass);
            out.flush();
        } catch (Throwable e) {
            FSTUtil.printEx(e);
            throw new RuntimeException(e);
        }
    }
};
