SRCDIR := src
SOURCES := $(shell find $(SRCDIR) -type f -name *.java) 
LIBS := -Djava.library.path=libs
EXEC := PancakePaintRunner

default: $(SOURCES)
	javac $^ -d bin

run: default
	java -cp bin $(LIBS) $(EXEC)

clean:
	find bin -type f -exec rm {}  +

.PHONY: clean
