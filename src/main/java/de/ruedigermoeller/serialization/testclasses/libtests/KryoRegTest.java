package de.ruedigermoeller.serialization.testclasses.libtests;

import de.ruedigermoeller.serialization.testclasses.basicstuff.LargeAndStrangeStrings;

import java.util.Collections;

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
public class KryoRegTest extends KryoTest {
    // If set, only JDK classes are pre-registered. Otherwise also benchmark 
    // specific classes are registered
	private boolean regOnlyJDK;

    public KryoRegTest(String title, boolean regOnlyJDK) {
        super(title);
        this.regOnlyJDK = regOnlyJDK;
    }

    public String getColor() {
        return "#A0A0A0";
    }

	@Override
	public void init() {
		super.init();

		// Register some often used JDK classes
		kryo.register(java.util.HashMap.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(java.util.ArrayDeque.class);
		kryo.register(java.util.concurrent.ConcurrentLinkedQueue.class);
		kryo.register(java.util.Vector.class);
		kryo.register(java.util.HashSet.class);
		kryo.register(java.util.LinkedList.class);
		kryo.register(java.util.Hashtable.class);
		kryo.register(java.util.concurrent.ConcurrentHashMap.class);
		kryo.register(java.util.TreeMap.class);
		kryo.register(java.lang.Exception.class);
		kryo.register(java.lang.RuntimeException.class);
		kryo.register(java.lang.IllegalArgumentException.class);
		kryo.register(java.util.Date.class);
		kryo.register(Object[].class);
		kryo.register(Object[][][].class);
		kryo.register(int[][][].class);
		kryo.register(char[][][].class);
		kryo.register(byte[][][].class);

		kryo.register(byte[].class);
		kryo.register(int[].class);
		kryo.register(char[].class);
		kryo.register(Integer[].class);

		// Support proper serialization of Exception classes
		kryo.register(Collections
		        .unmodifiableCollection(Collections.EMPTY_LIST).getClass());
		kryo.register(java.lang.ArrayIndexOutOfBoundsException.class);
		kryo.register(java.lang.StackTraceElement.class);
		kryo.getRegistration(java.lang.StackTraceElement.class)
		        .setInstantiator(
		                stdInstantiatorStrategy
		                        .newInstantiatorOf(java.lang.StackTraceElement.class));
		kryo.getRegistration(java.lang.Exception.class).setInstantiator(
		        stdInstantiatorStrategy
		                .newInstantiatorOf(java.lang.Exception.class));
		kryo.getRegistration(java.lang.IllegalArgumentException.class)
		        .setInstantiator(
		                stdInstantiatorStrategy
		                        .newInstantiatorOf(java.lang.IllegalArgumentException.class));
		kryo.getRegistration(java.lang.RuntimeException.class).setInstantiator(
		        stdInstantiatorStrategy
		                .newInstantiatorOf(java.lang.RuntimeException.class));
		kryo.getRegistration(java.lang.ArrayIndexOutOfBoundsException.class)
		        .setInstantiator(
		                stdInstantiatorStrategy
		                        .newInstantiatorOf(java.lang.ArrayIndexOutOfBoundsException.class));

		if (!regOnlyJDK) {
			// Register custom classes used by benchmarks
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.CommonCollections.MyComp.class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Primitives.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentPrimitives.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentPrimitivesExternalizable.class);

			// kryo.register(java.util.RegularEnumSet.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Primitives.SampleEnum.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Primitives.SpecialEnum.class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays.Dim.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays.Dim[].class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays.Dim[][].class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays.Dim[][][].class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.Trader.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.ObjectOrientedInt.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.ObjectOrientedDataType.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.SimpleOrder.class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.murks.common.ManagingManager.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.schwurbel.v1.ManagingDispatcherCoordinator.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.murks.common.special.common.UnManagedManager.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.schwurbel.kinda.xml.UnmanagedManagingManager.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.schwurbel.others.ManagingCoordinatorDispatcher.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.murks.common.special.common.util.UtilManager.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.wurschtel.NoniteratingObjectIteratorWrapperVisitor.class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.jdkcompatibility.ExternalizableTest.ExternalTest.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.jdkcompatibility.ExternalizableTest.NestedExternalTest.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentPrimitives[].class);
			kryo.register(LargeAndStrangeStrings.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.FrequentCollections.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.CommonCollections.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.enterprise.ManyClasses.class);
			kryo.register(de.ruedigermoeller.serialization.testclasses.jdkcompatibility.ExternalizableTest.class);

			kryo.register(de.ruedigermoeller.serialization.testclasses.basicstuff.HeavyNesting.class);
			kryo.register(data.media.Image.class);
			kryo.register(data.media.Media.class);
			kryo.getRegistration(
			        de.ruedigermoeller.serialization.testclasses.basicstuff.Primitives.class)
			        .setInstantiator(
			                stdInstantiatorStrategy
			                        .newInstantiatorOf(de.ruedigermoeller.serialization.testclasses.basicstuff.Primitives.class));
		}
	}
}
