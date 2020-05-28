default: SortIt.java LinkedList.java
	javac SortIt.java LinkedList.java

run: SortIt.class LinkedList.class
	java SortIt CashWords.txt

clean: SortIt.class LinkedList.class
	rm *.class
