package de.ruedigermoeller.serialization.testclasses.libtests;

import org.jboss.marshalling.*;
import org.jboss.marshalling.river.RiverMarshallerFactory;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
 * Date: 15.03.14
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
public class JBossRiver extends SerTest {

    private Marshaller marshaller = null;
    private Unmarshaller unmarshaller = null;

    public JBossRiver(String title) {
        super(title);
        try {
            MarshallingConfiguration cfg = new MarshallingConfiguration();
            MarshallerFactory factory = new RiverMarshallerFactory();
            marshaller = factory.createMarshaller(cfg);
            unmarshaller = factory.createUnmarshaller(cfg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void readTest(ByteArrayInputStream bin, Class cl) {
        try {
            InputStreamByteInput inputStreamByteInput = new InputStreamByteInput(bin);
            unmarshaller.start(inputStreamByteInput);
            resObject = unmarshaller.readObject();
            unmarshaller.finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeTest(Object toWrite, OutputStream bout, Class aClass) {
        try {
            marshaller.start(new OutputStreamByteOutput(bout));
            marshaller.writeObject(toWrite);
            marshaller.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

