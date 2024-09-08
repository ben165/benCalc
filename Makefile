PACKAGE = benCalc
all: $(PACKAGE)/Gui.class  $(PACKAGE)/Main.class

clean:
	rm -f $(PACKAGE)/*

run:
	java $(PACKAGE).Main

$(PACKAGE)/Gui.class: src/Gui.java
	javac src/Gui.java -d ""

$(PACKAGE)/Main.class: src/Main.java
	javac src/Main.java -d ""
