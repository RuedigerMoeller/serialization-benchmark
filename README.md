serialization-benchmark
=======================

a set of common java data structures designed to test validity and performance of a full graph serialization library.

main class to run tests: TestRunner. Check source for commandline options.

Run Json tests:

'java -Dignorewrong=true -cp [...] de.ruedigermoeller.serialization.testclasses.TestRunner -var cross -w 10000 -t 10000'

[json results](http://ruedigermoeller.github.io/fast-serialization/json_bench.html)

[fst 2.29 vs kryo 3.01](http://ruedigermoeller.github.io/fast-serialization/fst2.29vsKryo3.01.html)

[fst 2.50 vs kryo 4.0.0](http://ruedigermoeller.github.io/fast-serialization/results_fst2_Kryo4.html)

[older results incl jdk](https://github.com/RuedigerMoeller/fast-serialization/wiki/Benchmark)
