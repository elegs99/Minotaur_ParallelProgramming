import java.io.*;
import java.util.*;
import java.util.concurrent.atomic;

public class ParallelPrimeCounter {
  public static void main(String[] args) {
    int nGuests = 100;
    int totalCakesEaten = 0;
    atomicBoolean cupcake = new atomicBoolean();
    long start = System.currentTimeMillis();
    checkCupcake[] threads = new checkCupcake[nGuests];
    for (int i = 0; i < nGuests; i++) {
      threads[i] = new checkCupcake();
      threads[i].start();
    }
    while (totalCakesEaten < nGuests) {
      int randomGuest = (int)Math.random()*100;
      // send random guest to check cupcake
      threads[randomGuest].checkCupcake();
    }
    // only eat cupcake on first visit
    // only leader can ask to reset cupcake and he adds 1 to count each time he must reset
    // once count + 1 >= nGuest  annouce that every got a cupcake
  }
  class checkCupcake extends Thread {
    boolean eatenCupcake = false;
    public boolean checkCupcake() {
      // if master thread

        // if cupcake not there
          // add one to count
        // else
          // leave room

      // else
      // check state of cupcake
        // if cupcake there check if this thread has eaten
          // if eaten is true leave room

          // else
          // lock cupcake
          // eat it
          // unlock cupcake and leave room

        // else if not there leave room
      if (eatenCupcake == false) {
        // if master thread check if cupcake
      }
    }
  }
}
