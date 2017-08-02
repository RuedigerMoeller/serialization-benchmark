package de.ruedigermoeller.serialization.testclasses;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import de.ruedigermoeller.serialization.testclasses.basicstuff.*;
import de.ruedigermoeller.serialization.testclasses.basicstuff.Arrays;
import de.ruedigermoeller.serialization.testclasses.enterprise.*;
import de.ruedigermoeller.serialization.testclasses.jdkcompatibility.ExternalizableTest;
import de.ruedigermoeller.serialization.testclasses.json.BasicJSon;
import de.ruedigermoeller.serialization.testclasses.libtests.*;
import de.ruedigermoeller.serialization.testclasses.remoting.ShortRemoteCall;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ruedi
 * Date: 12.11.12
 * Time: 02:43
 * To change this template use File | Settings | File Templates.
 */
public class TestRunner {

    private static final String KRVERSION = "4.0.0";

    public TestRunner() {
    }


    Class testClass;
    public List<SerTest> runAll( Object toSer, int warmUP, int testRuns ) throws IOException, InterruptedException {
        testClass = toSer.getClass();

        System.out.println();
        System.out.println();
        System.out.println("************** Running all with "+toSer.getClass().getName()+" **********************************");
        
        for (int i = 0; i < mTests.size(); i++) {
            SerTest test = mTests.get(i);
            test.run(toSer, warmUP, testRuns);
        }
        for (int i = 0; i < mTests.size(); i++) {
            SerTest test = mTests.get(i);
            test.dumpRes();
        }

        charter.heading("Test Class: "+testClass.getSimpleName());
        Object testIns = null;
        try {
            if ( testClass.isArray() ) {
                testIns = testClass.getComponentType().newInstance();
            } else
                testIns = testClass.newInstance();
            if ( testIns instanceof HasDescription ) {
                charter.text(((HasDescription) testIns).getDescription());
                charter.text("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(mTests, new Comparator<SerTest>() {
            @Override
            public int compare(SerTest o1, SerTest o2) {
                return o1.getRWTimeNanos()-o2.getRWTimeNanos();
            }
        });
        List<Integer> hi = new ArrayList<>();
        List<Integer> lo = new ArrayList<>();
        List<Integer> siz = new ArrayList<>();
        String names[] = new String[mTests.size()];
        for (int i = 0; i < mTests.size(); i++) {
            SerTest serTest = mTests.get(i);
            if ( serTest.readIter == 0 || serTest.writeIter == 0 ) {
                lo.add(0);
                hi.add(0);
                siz.add(0);
                names[i] = serTest.title+" FAIL ";
            } else {
                int rv = serTest.getReadTimeNS ();
                int wv = serTest.getWriteTimeNanos();
                lo.add(rv);
                hi.add(rv+wv);
                siz.add(serTest.bout.size());
                if ( serTest.isEqualTestFailure() )
                    names[i] = serTest.title+"(ERR)";
                else
                    names[i] = serTest.title;
            }
        }
        charter.gChart(hi,lo,"speed read+write (ns)",names);
        charter.gChart(siz,siz,"size (bytes)",names);

        String format = "%-34s %14s %14s %14s %14s\n";
        String table = new Formatter().format( format, new Object[] {"lib", "read (ns)","write (ns)","total (ns)", "size (bytes)"} ).toString();
        for (int i = 0; i < mTests.size(); i++) {
            SerTest serTest = mTests.get(i);
            table += new Formatter().format(format,
                    names[i],
                    lo.get(i),
                    hi.get(i)-lo.get(i),
                    hi.get(i),
                    siz.get(i)
                    ).toString();
        }
        charter.text("<br><pre style='background-color:#ccc;'>"+table+"</pre><br>");
        

        return mTests;
    }


    List<SerTest> mTests = new ArrayList<>();
    public void registerTests() {
        if ( "default".equals(variants) ) {
            mTests.addAll(java.util.Arrays.asList(
//                    new FSTTest("FST 1", false, false), // unsafe and preferspeed deprecated unsupported since 1.43.
                    new FST20Test("FST 2", false, false)//
//                    new FSTJsonTest("FST JSON", false),
//                    new FST20Test("FST 2 Binary", true, false), // unsafe and preferspeed deprecated unsupported since 1.43.
//                    new KryoTest("Kryo "+KRVERSION)
//                new KryoUnsafeTest("Kryo "+KRVERSION+" Unsafe"),
//                    new JBossRiver("JBoss-River"),
//                    new JavaSerTest("Java built in"),
//                    new JBossSerializer("JBoss-Serializer")
            ));
        } else if ( "unsafe".equals(variants)) {
            mTests.addAll(java.util.Arrays.asList(
                    new KryoUnsafeTest("Kryo "+KRVERSION+" Unsafe"),
                    new KryoUnsafeRegTest("Kryo "+KRVERSION+" Unsafe pre-register JDK", true),
                    new KryoUnsafeRegTest("Kryo "+KRVERSION+" Unsafe pre-register all", false)
            ));
        } else if ( "cross".equals(variants)) {
            if ( tests.equals(testDefault) ) {
                tests = "asdfoinq"; // for jsonish tests use subset
            }
            mTests.addAll(java.util.Arrays.asList(
                    new FST20Test("FST Serialization 2.29 unshared", false, true),
                    new BoonTest("BOON .33 JSon") ,
                    new JSonIOTest("JSON IO") , // very slow
                    new GsonTest("Gson"),
                    new JacksonTest("Jackson 2.53"),
                    new FSTJsonTest("FST 2.29 Json over Jackson", false),
                    new JavaSerTest("JDK 1.8 Serialization", true)
//                    new FSTJsonTest("FST MinBin", true)
//                    new FSTTest("FST", false, false) // compare to fastest
//                    new JavaSerTTest("Java built in") // and standard
            ));
        } else {
            mTests.addAll(java.util.Arrays.asList(
//                    new FSTTest("FST 1", false, false), // unsafe and preferspeed deprecated unsupported since 1.43.
                    new FST20Test("FST 2", false, false), // unsafe and preferspeed deprecated unsupported since 1.43.
//                    new FST20Test("FST 2 Binary", true, false), // unsafe and preferspeed deprecated unsupported since 1.43.
                    new KryoTest("Kryo "+KRVERSION),
//                    new JBossRiver("JBoss-River"),
                    new JavaSerTest("Java built in")
//                    new JBossSerializer("JBoss-Serializer"),
//                    new KryoRegTest("Kryo "+KRVERSION+" pre-register JDK", true),
//                    new KryoRegTest("Kryo "+KRVERSION+" pre-register all", false),
//                    new KryoUnsafeTest("Kryo "+KRVERSION+" Unsafe"),
//                    new KryoUnsafeRegTest("Kryo "+KRVERSION+" Unsafe pre-register JDK", true),
//                    new KryoUnsafeRegTest("Kryo "+KRVERSION+" Unsafe pre-register all", false)
            ));
        }
    }

    HtmlCharter charter = new HtmlCharter("./result.html");
    String testDefault = "abcdefghijklmnopqrstuvwxyz";

    @Parameter(names = { "-warm", "-w" }, description = "number of warmup time ms >5000 for stable results")
    Integer warmup = 5000;
    @Parameter(names = { "-test", "-t" }, description = "number of test time ms  >5000 for stable results")
    Integer test = 5000;
    @Parameter(names = { "-cases", "-c" }, description = "testcases to execute (string of a..z, not specified: all)")
    String tests = testDefault;

    @Parameter(names = {"-help", "-h", "-?", "--help"}, help = true)
    private boolean help;

    @Parameter(names={"-variants", "-var"}, description = "default|unsafe|fst|all|cross")
    String variants = "default";

    //
    // example cmdline: -test 1000 -warm 2000 -cases adj
    //
    public static void main( String[] arg ) throws Exception {
        
        TestRunner runner = new TestRunner();
        JCommander jCommander = new JCommander(runner, arg);
        runner.registerTests();

        runner.charter.setAsc(new AsciiCharter("./result.txt"));

        runner.charter.openDoc();
        runner.charter.text("OS:" + System.getProperty("os.name"));
        runner.charter.text("JVM:" + System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
        runner.charter.text("CPU:" + System.getenv("PROCESSOR_IDENTIFIER") + " os-arch:" + System.getenv("PROCESSOR_ARCHITECTURE"));
        runner.charter.text("Cores (incl HT):" + Runtime.getRuntime().availableProcessors());
        runner.charter.text("<i>"+System.getProperty("java.runtime.version")+","+System.getProperty("java.vm.name")+","+System.getProperty("os.name")+"</i>");

        Object media = "";
        try {
            // read in the popular media benchmark
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./data/media_from_eisheye_test.os"));
            media = oin.readObject();
        } catch (Exception ex) {
            System.out.println("could not find media data for media test. Expect to run with project-root as working dir. Will skip test.");
        }

        Object testCases[] = {
                "a", FrequentPrimitives.getArray(10), // avoid measuring init overhead only for jboss, jdk 
                "b", FrequentPrimitivesExternalizable.getArray(10), // avoid measuring init overhead only for jboss, jdk
                "c", new FrequentCollections(1),
                "d", new LargeNativeIntArrays(1),
                "e", new LargeAndStrangeStrings(0),
                "f", new Primitives(),
                "g", Arrays.createPrimArray(),
                "h", new CommonCollections(),
                "i", Trader.generateTrader(101, true),
                "j", new ManyClasses(1),    // ref hashing, class writing
                "k", new ExternalizableTest(),  // performance of object*stream faking
                "l", new BigObject("dummy"),
                "m", HeavyNesting.createNestedObject(1000),
                "o", media,
                "p", new SmallThing(), // same as initial: measure effects of max hashsize on reuse speed
                "q", new LargeFPArrays(), // same as initial: measure effects of max hashsize on reuse speed
                "r", new BasicJSon(),
                "s", new JSonCollection(1),
                "n", new ShortRemoteCall(1),
        };

        for (int i = 0; i < testCases.length; i+=2) {
            String testLetter = (String) testCases[i];
            if ( runner.tests.indexOf(testLetter) >= 0 ) {
                Object testCase = testCases[i+1];
                if ( testCase != null ) // catch media file not found
                    runner.runAll(testCase,runner.warmup,runner.test);
            }
        }
        runner.charter.closeDoc();
    }
}
