package de.ruedigermoeller.serialization.testclasses.jdkcompatibility;


import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
Copyright [2014] Ruediger Moeller

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
        */

public class SpecialsTest {

    static class ToWrite implements Serializable{
        String dummy = "empty";

        public ToWrite(String dummy) {
            this.dummy = dummy;
        }

        public Object writeReplace() {
            return new ToRead(dummy);
        }
    }

    static class ToRead implements Serializable {
        String dummy;

        public ToRead(String dummy) {
            this.dummy = dummy;
        }

        public Object readResolve() {
            return dummy;
        }
    }

    public static void exceptionTest(FSTConfiguration conf) throws IOException, ClassNotFoundException {
        FSTObjectOutput out = conf.getObjectOutput();
        Exception e;
        try {
            throw new Exception("Test");
        } catch (Exception ex) {
            e = ex;
        }
        out.writeObject(e);
        out.flush();
        FSTObjectInput in = new FSTObjectInput(conf);
        in.resetForReuseUseArray(out.getBuffer(),out.getWritten());
        Object ex = in.readObject();
        System.out.println("success "+ex);
    }

    public static void main(String[]s) throws IOException, ClassNotFoundException {
        double d = 1345.23423;
        long l = (long) d;
        long l1 = (long) ((d-l)*100000);
        double d1 = l+(double)l1/100000d;

        System.out.println( d==d1 );
        System.out.println( d+" "+d1);
//        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//        exceptionTest(conf);
//
//
//        ToWrite w = new ToWrite("bla");
//
//        byte b[] = null;
//        FSTObjectOutput out = new FSTObjectOutput(conf);
//        out.writeObject(w);
//        out.flush();
//        b = out.getBuffer();
//
//        FSTObjectInput in = new FSTObjectInput(conf);
//        in.resetForReuseUseArray(b,b.length);
//        Object res = in.readObject();
//
//        if ( !res.equals("bla") ) {
//            throw new RuntimeException("fail "+res);
//        }

    }

}
