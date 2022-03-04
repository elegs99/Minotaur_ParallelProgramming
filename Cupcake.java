import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class Cupcake {
  public static void main(String[] args) {
    int nGuests = 1000;
    int totalCakesEaten = 0;
    final AtomicBoolean cupcakeIsThere = new AtomicBoolean(true);

    CheckCupcake[] threads = new CheckCupcake[nGuests];
    for (int i = 0; i < nGuests; i++) {
      threads[i] = new CheckCupcake();
      threads[i].start();
    }
    long start = System.currentTimeMillis();
    long masterThreadID = threads[0].getId();
    while (totalCakesEaten + 1 < nGuests) {
      Random r = new Random();
      int randomGuest = r.nextInt(nGuests);
      // send random guest to check cupcake
      if (randomGuest == 50) {
        if (threads[randomGuest].CheckCupcake(true, cupcakeIsThere, totalCakesEaten)) {
          totalCakesEaten++;
        }
      }
      else {
        threads[randomGuest].CheckCupcake(false, cupcakeIsThere, totalCakesEaten);
      }
      System.out.println("The count is " + totalCakesEaten);
    }
  }
}
class CheckCupcake extends Thread {
  boolean eatenCupcake = false;
  public boolean CheckCupcake(boolean masterThread, AtomicBoolean cupcakeIsThere, int totalCakesEaten) {
    // if master thread
    if (masterThread == true) {
      // if cupcake not there
      if (cupcakeIsThere.compareAndSet(false, true)) {
          // add one to count
          return true;
      }
      // else leave room
      return false;
    }
    else {
      // check state of cupcake
      if (cupcakeIsThere.get() == true) {
        // if cupcake there check if this thread has eaten
        if (eatenCupcake == false) {
          // lock cupcake
          cupcakeIsThere.compareAndSet(true, false);
          eatenCupcake = true;
          // unlock cupcake
        }
      }
      return false;
    }
  }
}
