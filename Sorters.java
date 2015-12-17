public class Sorters{

  //Debugfunktion, die überprüft, ob das array wirklich sortiert ist. Die Laufzeit wird zur Zeit zurückgegeben, was man optional
  //  als Vergleichswert mit den Sortieralgorithmen zeigen könnte.
  long verify(int[] array){
    long start = System.nanoTime();

    int good = 1;
    int len = array.length;
    for(int pos = 0; pos < len - 1; pos++){
      if (array[pos] > array[pos + 1]){
        good = 0;
        break;
      }
    }
    if (good != 1){
      //Alternativ (wenn mit Fenstern gearbeitet wird) könnte eine Klassenvariable "good" auf 0 gesetzt werden.
      System.out.println("Fehler: Array wurde nicht richtig sortiert.");
    }

    long stop = System.nanoTime();
    return stop - start;
  }

  //Sortiert "len" Werte in "array" mit quicksort. Die sortierten Werte brauchen wir nicht zurückgeben. (?)
  long quicksort(int[] array){
    //Gibt Zeit in Nanosekunden zurück
    long start = System.nanoTime();

    //Hier darf jemand das eigentliche Sortierten implementieren.

    long stop = System.nanoTime();
    return stop - start;
  }

  //Wandelt Nanosekunden um in Zeitangabe, z.B. 5036 -> "5,036µs"
  String timestring(long time){
    //TODO
    return "blah";
  }
}
