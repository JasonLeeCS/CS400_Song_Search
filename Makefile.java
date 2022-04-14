//run: compile
//                
//        echo "The commands to run the program are listed here"
//        java TextUITester
//
//compile: TextUITester.java SearchFrontEnd.java SongSearchTests.java
//
//        echo "This rule compiles all necessary java source files"
//        #javac SearchFrontEnd.java
//        javac SongSearchTests.java
//        javac TextUITester.java
//
//test: compile
//        
//        java SongSearchTests
//
//clean:
//        echo "Removes *.class files"
//        rm *.class
