SRCDIR := src
SOURCES := $(shell find $(SRCDIR) -type f -name *.java)
LIBS := -Djava.library.path=libs
EXEC := PancakePaintRunner
CP := -cp .:lib/Jama-1.0.3.jar:lib/commons-math3-3.5.jar:bin

default: $(SOURCES)
	javac $(CP) $^ -d bin

run: default
	java $(CP) $(LIBS) $(EXEC)

clean:
	find bin -type f -exec rm {}  +

.PHONY: clean
