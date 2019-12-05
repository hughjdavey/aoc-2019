# aoc-2019

Attempts at [Advent of Code 2019](https://adventofcode.com/2019) in [Kotlin](https://kotlinlang.org/), trying as hard as possible to stay functional and lazy. Project layout and structure is based on my [aoc-kotlin-starter](https://github.com/hughjdavey/aoc-kotlin-starter) project. See below for how to run the project :)

See [Bogdanp's Awesome Advent of Code](https://github.com/Bogdanp/awesome-advent-of-code) for a great collection of starter and solution repositories in multiple languages.

### Running

* Navigate to top-level directory on the command line
* Run `./gradlew run` to run all days
* Run `./gradlew run --args $DAY` where `$DAY` is an integer to run a specific day

### Testing

* Navigate to top-level directory on the command line
* Run `./gradlew test`
* Run `./gradlew test --tests $TEST_CLASS` where `$TEST_CLASS` is a string name of a test class, including package, e.g. 'days.Day4Test'
