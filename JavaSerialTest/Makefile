CLASSPATH := $(CLASSPATH):/home/isaac/packages/rxtx-2.1-7-bins-r2/RXTXcomm.jar:.
LIBS := -Djava.library.path=/home/isaac/packages/arduino-1.6.6/lib

default: SerialTest.class
	java $(LIBS) -cp $(CLASSPATH) SerialTest

SerialTest.class: SerialTest.java
	javac -classpath $(CLASSPATH) SerialTest.java

clean:
	rm *.class

.PHONY: clean
