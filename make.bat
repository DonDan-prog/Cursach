@javac ./SortAlgs/*.java
@javac *.java

@jar -cvfe VisualSort.jar VisualSort *.class SortAlgs/*.class
@del *.class
@cd ./SortAlgs
@del *.class
@cd ../