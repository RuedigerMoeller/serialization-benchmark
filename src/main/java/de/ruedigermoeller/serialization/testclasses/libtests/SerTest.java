package de.ruedigermoeller.serialization.testclasses.libtests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.cedarsoftware.util.DeepEquals;

/**
 * Copyright (c) 2012, Ruediger Moeller. All rights reserved.
 * <p/>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * <p/>
 * Date: 16.06.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class SerTest {

    public String title;
    public ByteArrayOutputStream bout = new ByteArrayOutputStream(100000);
    ByteArrayInputStream bin;
    public long timWrite;
    public long timRead;
    int length;
    Object testObject;
    protected Object resObject;

    public SerTest(String title) {
        this.title = title;
    }

    public int readIter = 0;
    public int writeIter = 0;
    public void run(Object toWrite, int warmupTime, int testTimeMS) {
        testObject = toWrite;
        readIter = 0;
        writeIter = 0;

        System.out.println("==================== Run Test "+title);
        System.out.println("warmup ..");
        
        long startTim = 0;
        startTim = System.currentTimeMillis();
        while ( System.currentTimeMillis()-startTim < warmupTime ) {
            for ( int i = 0; i < 500; i++ ) {
                try {
                    runOnce(toWrite);
                } catch (Throwable th) {
                    break;
                }
            }
        }

        System.gc();
        System.out.println("write ..");
        try {
            startTim = System.currentTimeMillis();
            while ( System.currentTimeMillis()-startTim < testTimeMS ) {
                for ( int i = 0; i < 1000; i++ ) {
                    runWriteTest(toWrite);
                }
                writeIter++;
            }
            timWrite = System.currentTimeMillis()-startTim;
        } catch (Exception e) {
            System.out.println(""+title+" FAILURE in write "+e.getMessage());
        }
        length = bout.toByteArray().length;

        System.gc();
        try {
            System.out.println("read ..");
            startTim = System.currentTimeMillis();
            while ( System.currentTimeMillis()-startTim < testTimeMS ) {
                for ( int i = 0; i < 1000; i++ ) {
                    runReadTest(toWrite.getClass());
                }
                readIter++;
            }
            timRead = System.currentTimeMillis()-startTim;
        } catch (Exception e) {
            timRead = 0;
            e.printStackTrace();
            System.out.println(""+title+" FAILURE in read "+e.getMessage());
        }
        if ( resObject != null ) {
            if ( ! DeepEquals.deepEquals(resObject, toWrite) ) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! EqualTest failed !!!!!!!!!!!!!!!!!!!!!!!!!!!");
                length = 0;
                timRead = 0;
                timWrite = 0;
            } else {
                System.out.println("+++++++++++++++++ EqualTest succeed ++++++++++++++++++++");
            }
        }
    }

    public void runOnce( Object toWrite ) {
        runWriteTest(toWrite);
        runReadTest(toWrite.getClass());
    }

    public void dumpRes() {
        try {
        System.out.println(title+" : Size:"+length+",  TimeRead: "+(timRead*1000/readIter)+" ns,   TimeWrite: "+(timWrite*1000/writeIter)+" ns ");
        } catch (Exception e) {
            System.out.println("** Exception in dump"+e.getMessage());
        }
    }

    public void runReadTest(Class cl) {
        bin = new ByteArrayInputStream(bout.toByteArray());
        readTest(bin, cl);
    }

    public void runWriteTest( Object toWrite ) {
        bout.reset();
        writeTest(toWrite, bout, toWrite.getClass());
    }

    protected abstract void readTest(ByteArrayInputStream bin, Class cl);

    protected abstract void writeTest(Object toWrite, OutputStream bout, Class aClass);

    public String getColor() {
        return "#a04040";
    }
}
