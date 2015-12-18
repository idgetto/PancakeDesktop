SRCDIR := src
BINDIR := bin
SOURCES := $(shell find $(SRCDIR) -type f -name *.java)
LIBS := -Djava.library.path=libs
EXEC := PancakePaintRunner
CP := -cp .:lib/Jama-1.0.3.jar:lib/commons-math3-3.5.jar:bin

default: $(SOURCES)
	javac $(CP) $^ -d $(BINDIR)

run: default
	java $(CP) $(LIBS) $(EXEC)

jar: $(SOURCES)
	cp lib/*.jar $(BINDIR)
	cd $(BINDIR) && jar -cvfm ../pp.jar ../MANIFEST.MF * *.jar; cd -

clean:
	find $(BINDIR) -type f -exec rm {}  +

.PHONY: clean
