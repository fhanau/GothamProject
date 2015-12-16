public class Sorters{

  //Sortiert "len" Werte in "array" mit quicksort. Die sortierten Werte brauchen wir nicht zurückgeben. (?)
  long quicksort(long[] array, long len){
    //Gibt Zeit in Nanosekunden zurück
    long start = System.nanoTime();

    //Hier darf jemand das eigentliche Sortierten implementieren.

    long stop = System.nanoTime();
    return stop - start;
  }

  //Weitere Sortierverfahren im gleichen Format
  long bubblesort(long[] array, long len){
    return 0;
  }
  long insertionsort(long[] array, long len){
    return 0;
  }


  //Wandelt Nanosekunden um in Zeitangabe, z.B. 5036 -> "5,036µs"
  String timestring(long time){
    //TODO
    return "blah";
  }
}
