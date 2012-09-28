all:
	javac -cp DerbyPrototype/derby.jar *.java -nowarn
	
clean:
	rm -r *.class
