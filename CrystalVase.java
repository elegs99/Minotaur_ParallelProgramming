import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class CrystalVase {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("How many guests: ");
    int nGuests = scan.nextInt();
    int totalGuestsVisited = 0;
    final AtomicBoolean roomAvailable = new AtomicBoolean(true);
    CrystalGuest[] threads = new CrystalGuest[nGuests];
    for (int i = 0; i < nGuests; i++) {
      threads[i] = new CrystalGuest();
      threads[i].start();
    }
    while (totalGuestsVisited < nGuests) {
      Random r = new Random();
      int randomGuest = r.nextInt(nGuests);
      if (roomAvailable.get() == true) {
        if (threads[randomGuest].CrystalGuest(roomAvailable)) {
          totalGuestsVisited++;
        }
      }
      // System.out.println("The count is " + totalGuestsVisited);
    }
    System.out.println("Every guest has visited the crystal room at least once");
  }
}
class CrystalGuest extends Thread {
  boolean visitedRoom = false;
  public boolean CrystalGuest(AtomicBoolean roomAvailable) {
    if (roomAvailable.compareAndSet(true, false)) {
      if (!visitedRoom) {
        visitedRoom = true;
        roomAvailable.set(true);
        return true;
      }
      roomAvailable.set(true);
      return false;
    }
    return false;
  }
}
