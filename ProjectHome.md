Clojure has a number of fantastic immutable data types (maps, lists, and sets), and I want to be able to use them directly in my Java programs. Using immutable data stuctures, among other benefits, makes writing multithreaded code almost trivial.

Anyway, all the immutable data types in Clojure implement the interfaces from the `java.util` package. So all they need is a little a little spit and polish.

This project is still in the early stages of development. I am still working on a few core concepts, including Java patterns for composition when using immutable types. I'll put up some examples once I work it out.

Projects that implement immutable data types for Java include [Functional Java](http://www.functionaljava.org/) and [Google Guava](https://code.google.com/p/guava-libraries/).