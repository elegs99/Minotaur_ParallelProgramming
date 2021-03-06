import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class Cupcake {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("How many guests: ");
    int nGuests = scan.nextInt();
    int totalCakesEaten = 0;
    final AtomicBoolean cupcakeIsThere = new AtomicBoolean(true);

    CheckCupcake[] threads = new CheckCupcake[nGuests];
    for (int i = 0; i < nGuests; i++) {
      threads[i] = new CheckCupcake();
      threads[i].start();
    }
    while (totalCakesEaten + 1 < nGuests) {
      Random r = new Random();
      int randomGuest = r.nextInt(nGuests);
      // send random guest to check cupcake
      if (randomGuest == 0) {
        if (threads[randomGuest].CheckCupcake(true, cupcakeIsThere)) {
          totalCakesEaten++;
        }
      }
      else {
        threads[randomGuest].CheckCupcake(false, cupcakeIsThere);
      }
      // System.out.println("The count is " + totalCakesEaten);
    }
    System.out.println("Every guest has visited the labyrinth at least once");
  }
}
class CheckCupcake extends Thread {
  boolean eatenCupcake = false;
  public boolean CheckCupcake(boolean masterThread, AtomicBoolean cupcakeIsThere) {
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
          cupcakeIsThere.compareAndSet(true, false);
          eatenCupcake = true;
        }
      }
      return false;
    }
  }
}
