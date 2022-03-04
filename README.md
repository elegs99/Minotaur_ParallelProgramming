# Minotaur_ParallelProgramming

Problem 1:<br>
  My approach to solving this problem was based on the prisoner lightswitch problem presented in class. Similarly my program selects a master thread which keeps track of how many times the cupcake goes missing. All other threads are instructed to only eat the cupcake on their first visit. To check the validatiy of my results I increased the number of guests and witnessed an exponetial increase in runtime. This logic follows as there would be exponentially less chance of a new guest being selected each iteration as the amount of overall guests increases.

Problem 2:<br>
  The best strategy for the threads to follow is a busy-wait queue aka. option 3. Since we have so many threads representing guests we should pick the startegy which leads to the least contention. With the other 2 methods as soon as the room is availble every guest would try to enter it leading to all the availble threads fighting for that 1 spot. One of the disadvantges of a queue based line is the complexity in setting up such a data structure.
  
  I choose to implement strategy 2 since in my opinion it was the most straight foward approach. Although strategy 3 stood out to me as the most efficent for parallel processing I was worried that I may code it incorrectly.

  I based my approach to this problem on the code I wrote for the first problem. I managed to adapt the code by removing the cupcake eating logic and replacing it with the available/busy flag logic. The runtime of this algorithim is much faster then the first I believe this is because the guest don't do much in the room aka their is very little code that each thread has to excute. I ensured the validaty of my algorithim through the same method as the first problem. As I increased the number of guest I saw an exponential increase in runtime.

<br><br>

To run my programs first navigate to directory with the two java files then enter the following commands in your terminal:

javac Cupcake.java<br>
java Cupcake

javac CrystalVase.java<br>
java CrystalVase
