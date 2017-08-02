serialization-benchmark
=======================

a set of common java data structures designed to test validity and performance of a full graph serialization library.

**How to run:**

main class: TestRunner creates a file in [workingdir]/results.html

* comment/uncomment serialization libs in TestRunner.registerTests()
* for more exact tests use command line options "-w 20000 -t 20000" (=warmuptime, testtime. default is 5000)

[json results](http://ruedigermoeller.github.io/fast-serialization/json_bench.html)

[fst 2.29 vs kryo 3.01](http://ruedigermoeller.github.io/fast-serialization/fst2.29vsKryo3.01.html)

[fst 2.50 vs kryo 4.0.0](http://htmlpreview.github.io/?https://github.com/RuedigerMoeller/serialization-benchmark/blob/master/result_fst2_kryo4.html)

[older results incl jdk](https://github.com/RuedigerMoeller/fast-serialization/wiki/Benchmark)
