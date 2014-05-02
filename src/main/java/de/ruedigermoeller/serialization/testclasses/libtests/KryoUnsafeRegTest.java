package de.ruedigermoeller.serialization.testclasses.libtests;

import com.esotericsoftware.kryo.io.UnsafeInput;
import com.esotericsoftware.kryo.io.UnsafeOutput;



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
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class KryoUnsafeRegTest extends KryoRegTest {

    public KryoUnsafeRegTest(String title, boolean regOnlyJDK) {
        super(title, regOnlyJDK);
    }

    public String getColor() {
        return "#A0A0A0";
    }

    @Override
    public void init() {
    	super.init();
        in = new UnsafeInput(500*1000);
        output = new UnsafeOutput(500*1000);
    }

}
