## kya Lambda, abstract class ke sath work karta hai ?
if we have an abstract class with just one abstract method, lambda does not work. Lambda works only with Functional Interface i.e. interface with just one abstract method.

## Why not ?
~~Find the reason later~~. Here is the reason :
	- Abstract class does not guarantee that it will always have only one abstract method. In future we could add more abstract methods in the abstract class.
	- But FunctionalInterface guarantee that there will always be only one abstract method present in the interface.