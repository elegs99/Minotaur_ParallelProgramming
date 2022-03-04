# Minotaur_ParallelProgramming

Problem 1:
  

Problem 2:

  The best strategy for the threads to follow is a busy-wait queue aka. option 3. Since we have so many threads representing guests we should pick the startegy which leads to the least contention. With the other 2 methods as soon as the room is availble every guest would try to enter it leading to all the availble threads fighting for that 1 spot. One of the disadvantges of a queue based line is the complexity in setting up such a data structure.
  
  I choose to implement strategy 2 since in my opinion it was the most straight foward approach. Although strategy 3 stood out to me as the most efficent for parallel processing I was worried that I may code it incorrectly.

To run my programs first navigate to directory with the two java files then enter the following commands in your terminal:

javac Cupcake.java
java Cupcake

javac CrystalVase.java
java CrystalVase
