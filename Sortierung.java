import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Sortierung extends Frame {
  
  private Button bNewArray = new Button();
  private Button bQuickSort = new Button();                                                                                                                                                                                                                                                                        
  private Button bInsertionSort = new Button();                                                                                                                                                                                                                                                     
  private Button bBubbleSort = new Button();
  private Button bGnomeSort = new Button();    
  private Button bBogoSort = new Button(); 
  private Button bCountingSort = new Button();                                                                                                                                                                                                                                        
  private Label lStatusQuickSort = new Label();
  private Label lStatusGnomeSort = new Label();
  private Label lStatusBubbleSort = new Label();
  private Label lStatusInsertionSort = new Label();
  private Label lStatusBogoSort = new Label();
  private Label lStatusCountingSort = new Label();
  private Label lQuickSort = new Label();
  private Label lGnomeSort = new Label();
  private Label lBubbleSort = new Label();
  private Label lInsertionSort = new Label();
  private Label lBogoSort = new Label();
  private Label lCountingSort = new Label();
  public int[] array;
  public int[] arrayQuickSort;
  public int[] arrayInsertionSort;
  public int[] arrayBubbleSort;
  public int[] arrayGnomeSort;
  public int[] arrayBogoSort;
  public int[] arrayCountingSort;
  private Label lLaenge = new Label();
  private NumberField nfArrayLength = new NumberField();
  private List list = new List();
  
  private Label lDauer = new Label();
  private Choice cArrayTyp = new Choice();
  private Label lArt = new Label();
  private Checkbox cntcheck = new Checkbox("F\u00FCr CountingSort optimiert");
  
  int readyforcountingsort = 0;
  //Hilfsfunktioen
  
  //Wandelt Nanosekunden um in Zeitangabe, z.B. 5036 -> "5,036us"
  String timestring(long time){
    //TODO
    return Long.toString(time);
  }
  
  //Ueberprueft, ob das Array sortiert ist.
  boolean pruefen(int[] array){
    int len = array.length;
    for(int i = 0; i < len - 1; i++){
      if (array[i] > array[i + 1]){
        return false;
      }
    }
    return true;
  }
  
  //"Schuettelt" das Array: Die Werte bleiben erhalten, bekommen aber eine neue Position.
  void schuetteln(int[] array){
    Random rn = new Random();
    int len = array.length;
    for(int i = 0; i < len - 1; i++){
      int rnd = rn.nextInt(len - i);
      int temp = array[i];
      array[i] = array[i + rnd];
      array[i + rnd] = temp;
    }
  }
  
  //Erzeugt ein Array und fuellt es mit zufaelligen Werten.
  int[] getRandomArray(int len){
    int[] array = new int[len];
    Random rn = new Random();
    for (int i = 0; i < len; len++) {
      array[i] = rn.nextInt();
    }
    return array;
  }
  
  //Erzeugt ein Array und fuellt es mit zufaelligen Werten <= 255.
  int[] getRandomArraycnt(int len){
    int[] array = new int[len];
    Random rn = new Random();
    for (int i = 0; i < len; len++) {
      array[i] = rn.nextInt(255);
    }
    return array;
  }
  
  //ALGORITHMEN
  
  //Implementiert Bogosort: Die Werte werden so lange zufaellig gemischt, bis sie sortiert sind. Komplexitaet O(n * n!). Bricht nach 5 Sekunden ab.
  void bogosort(int[] array){
    long start = System.nanoTime();
    while(pruefen(array) != true && (System.nanoTime() - start < 5000000000L)){
      schuetteln(array);
    }
  }
  
  //Implementiert Insertionsort: Dabei wird einem leeren Array immer ein Wert hinzugefuegt, der dann an die passede Position bewegt wird. Komplexitaet O(n^2).
  void insertionsort(int[] array){
    
     int len = array.length;
    for(int i = 1; i < len; i++){
      int j = i;
      while(j > 0 && array[j - 1] > array[j]){
        int temp = array[j];
        array[j] = array[j - 1];
        array[j - 1] = temp;
        j--;
      }
    }
    
    
    
    /*int len = array.length;
    for(int i = 0; i < len; i++){
      int temp = array[i];
      int pos = i;
      while(pos > 0 && temp < array[pos - 1]){
        pos--;
      }
      array[pos] = temp;
    }   */
  }
  
  //Implementiert Countingsort: Funktioniert in dieser Implementierung nur bei natuerlichen Zahlen <= 255. Zaehlt, wie oft jeder moegliche Wert vorkommt und schreibt dann jeden dieser Werte mit der jeweiligen Haeufigkeit zurueck in das Array. Komplexitaet O(n + 256)
  void countingsort(int[] array){
    int len = array.length;
    int[] cnt = new int[256];
    for(int i = 0; i < 256; i++){
      cnt[i] = 0;
    }
    for(int i = 0; i < len; i++){
      cnt[array[i]]++;
    }
    int idx = 0;
    for(int i = 0; i < 256; i++){
      for (int j = 0; j < cnt[i]; j++) {
        array[idx] = i;
        idx++;
      } 
    }
  }
  
  //Fuer quicksort
  static int teile(int[] array, int links, int rechts) {       //Funktion von links und rechts wie erstes und letztes bei quicksort()
    int i = links - 1;
    int pivot = array[rechts];
                       
    for (int j = links; j < rechts; j++) {
      if (array[j] <= pivot) {
        i++;
        int x=array[i];
        array[i]=array[j];
        array[j]=x;
      }
    }
    int x=array[i + 1];
    array[i + 1]=array[rechts];
    array[rechts]=x;
    
    return i + 1;
  }
  
  void quicksort(int[] array, int erstes, int letztes){ //erstes ist der Index des ersten Elements der (Teil-)Liste, letztes der Index des letzten
    if (erstes < letztes) {
      int mitte = teile(array, erstes, letztes);      //mitte ist der Index des Pivot-Elements nach der Sortierung durch teile()
      quicksort(array, erstes, mitte-1);        //linke Teilliste wird sortiert
      quicksort(array, mitte+1, letztes);         //rechte Teilliste wird sortiert
    }
  }
  
  //GUI
  public Sortierung(String title) { 
    super(title);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
    });
    setSize(625, 500);  
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Panel cp = new Panel(null);
    add(cp);
    
    bNewArray.setBounds(392, 40, 75, 25);
    bNewArray.setLabel("Neues Array");
    bNewArray.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bNewArray_ActionPerformed(evt);
      }
    });
    cp.add(bNewArray);
    
    enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    
    
    bQuickSort.setBounds(368, 152, 75, 25);
    bQuickSort.setLabel("sortieren");
    bQuickSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bQuickSort_ActionPerformed(evt);
      }
    });
    cp.add(bQuickSort);
    bInsertionSort.setBounds(368, 192, 75, 25);
    bInsertionSort.setLabel("sortieren");
    bInsertionSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bBinaryTree_ActionPerformed(evt);
      }
    });
    cp.add(bInsertionSort);
    bBubbleSort.setBounds(368, 232, 75, 25);
    bBubbleSort.setLabel("sortieren");
    bBubbleSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bBubbleSort_ActionPerformed(evt);
      }
    });
    cp.add(bBubbleSort);
    bGnomeSort.setBounds(368, 272, 75, 25);
    bGnomeSort.setLabel("sortieren");
    bGnomeSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGnomeSort_ActionPerformed(evt);
      }
    });
    cp.add(bGnomeSort);
    
    bBogoSort.setBounds(368, 312, 75, 25);
    bBogoSort.setLabel("sortieren");
    bBogoSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bBogoSort_ActionPerformed(evt);
      }
    });
    cp.add(bBogoSort);
    
    bCountingSort.setBounds(368, 352, 75, 25);
    bCountingSort.setLabel("sortieren");
    bCountingSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCountingSort_ActionPerformed(evt);
      }
    });
    cp.add(bCountingSort);
    
    
    lStatusQuickSort.setBounds(240, 152, 110, 20);
    lStatusQuickSort.setText("?");
    cp.add(lStatusQuickSort);
    lStatusGnomeSort.setBounds(240, 272, 110, 20);
    lStatusGnomeSort.setText("?");
    cp.add(lStatusGnomeSort);
    lStatusBubbleSort.setBounds(240, 232, 110, 20);
    lStatusBubbleSort.setText("?");
    cp.add(lStatusBubbleSort);
    lStatusInsertionSort.setBounds(240, 192, 110, 20);
    lStatusInsertionSort.setText("?");
    cp.add(lStatusInsertionSort);
    lStatusBogoSort.setBounds(240, 312, 110, 20);
    lStatusBogoSort.setText("?");
    cp.add(lStatusBogoSort);
    lStatusCountingSort.setBounds(240, 352, 110, 20);
    lStatusCountingSort.setText("?");
    cp.add(lStatusCountingSort);
    lQuickSort.setBounds(112, 152, 110, 20);
    lQuickSort.setText("QuickSort");
    cp.add(lQuickSort);
    lGnomeSort.setBounds(112, 272, 110, 20);
    lGnomeSort.setText("GnomeSort");
    cp.add(lGnomeSort);
    lBubbleSort.setBounds(112, 232, 110, 20);
    lBubbleSort.setText("BubbleSort");
    cp.add(lBubbleSort);
    lInsertionSort.setBounds(112, 192, 110, 20);
    lInsertionSort.setText("InsertionSort");
    cp.add(lInsertionSort);
    lBogoSort.setBounds(112, 312, 110, 20);
    lBogoSort.setText("BogoSort");
    cp.add(lBogoSort);
    lCountingSort.setBounds(112, 352, 110, 20);
    lCountingSort.setText("CountingSort");
    cp.add(lCountingSort);
    
    lLaenge.setBounds(112, 40, 110, 20);
    lLaenge.setText("L\u00E4nge des Arrays");
    cp.add(lLaenge);
    nfArrayLength.setBounds(240, 40, 107, 20);
    nfArrayLength.setText("10000");
    cp.add(nfArrayLength);
    
    lDauer.setBounds(240, 112, 110, 20);
    lDauer.setText("Dauer:");
    cp.add(lDauer);
    cArrayTyp.setBounds(240, 16, 150, 20);
    cp.add(cArrayTyp);
    lArt.setBounds(112, 16, 110, 20);
    lArt.setText("Art des Arrays");
    cp.add(lArt);
    cArrayTyp.add("zuf\u00E4llig");
    cArrayTyp.add("sortiert");
    cArrayTyp.add("r\u00FCckw\u00E4rts sortiert");
    
    cntcheck.setBounds(112, 55, 180, 50);
    cp.add(cntcheck);
    
    //Sortierung ist erst mit erstelltem Array moeglich
    bQuickSort.setEnabled(false);
    bBogoSort.setEnabled(false);
    bInsertionSort.setEnabled(false);
    bGnomeSort.setEnabled(false);
    bCountingSort.setEnabled(false);
    bBubbleSort.setEnabled(false);
        
        
    setVisible(true);
  }
  
  public void resetLabels() {
    lStatusQuickSort.setBackground(Color.white);
    lStatusInsertionSort.setBackground(Color.white);
    lStatusBubbleSort.setBackground(Color.white);
    lStatusGnomeSort.setBackground(Color.white);
    lStatusBogoSort.setBackground(Color.white);
    lStatusCountingSort.setBackground(Color.white);
    
    lStatusQuickSort.setText("?");
    lStatusInsertionSort.setText("?");
    lStatusBubbleSort.setText("?");
    lStatusGnomeSort.setText("?");
    lStatusBogoSort.setText("?");
    lStatusCountingSort.setText("?");
  }
  
  public void turnArray(){               //Umdrehen durch Dreieckstaeusche
    for(int i=0; i<array.length/2; i++){
      int zwischenspeicher=array[i];
      array[i]=array[array.length-1-i];
      array[array.length-1-i]=zwischenspeicher;
    }
  }
  
  public void bNewArray_ActionPerformed(ActionEvent evt) {
    readyforcountingsort = cntcheck.getState() ? 1 : 0;
    int length;
    if (nfArrayLength.getInt()!=0) {
      length = nfArrayLength.getInt();
    }
    else {
      length = 10000;
    }
    
    array = readyforcountingsort == 1 ? getRandomArraycnt(length) : getRandomArray(length);
    
    if (cArrayTyp.getSelectedItem() != "zuf\u00E4llig") {     //wenn das Array sortiert sein soll, wird es sortiert.
      quicksort(array, 0, length - 1);
      
      if (cArrayTyp.getSelectedItem().equals("r\u00FCckw\u00E4rts sortiert")) {  //bei "rueckwaerts sortiert" wird das bereits sortierte Array umgedreht.
        turnArray();
      }
    }
    arrayQuickSort=array.clone();
    arrayInsertionSort=array.clone();
    arrayBubbleSort=array.clone();
    arrayBogoSort=array.clone();
    arrayCountingSort=array.clone();
    arrayGnomeSort=array.clone();   //damit alle Sortierverfahren nacheinander ein Array sortieren koennen, wird das Array fuer jedes Verfahren dupliziert.
    
    resetLabels();
    
    bQuickSort.setEnabled(true);
    bBogoSort.setEnabled(true);
    bInsertionSort.setEnabled(true);
    bGnomeSort.setEnabled(true);
    bCountingSort.setEnabled(readyforcountingsort == 1 ? true : false);
    bBubbleSort.setEnabled(true);
    
    nfArrayLength.setBackground(Color.green);
  }
  
  public void bQuickSort_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();    //Startzeitpunkt ermitteln
    
    //pruefen(arrayQuickSort);
    quicksort(arrayQuickSort, 0, arrayQuickSort.length - 1);
    
    long stop = System.nanoTime();   //Stopzeitpunkt ermitteln
    
    String time=timestring(stop - start);   //timestring() gibt Differenz zwischen stop und start als String aus 
    if (pruefen(arrayQuickSort) != true) {
      time = "error";
    }
    
    lStatusQuickSort.setBackground(Color.green);
    lStatusQuickSort.setText(time);  //Dauer anzeigen
  }
  
  public void bBinaryTree_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();
    
    insertionsort(arrayInsertionSort);
    
    long stop = System.nanoTime();

    String time = timestring(stop - start);   //wie oben
    if (pruefen(arrayInsertionSort) != true) {
      time = "error";
    }
    lStatusInsertionSort.setBackground(Color.green);
    lStatusInsertionSort.setText(time);
  }
  
  public void bBubbleSort_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();
    
    //FELIX: Hier darf jemand das eigentliche Sortierten implementieren.
    
    long stop = System.nanoTime();
    
    String time = timestring(stop - start); //wie oben
    lStatusBubbleSort.setBackground(Color.green);
    lStatusBubbleSort.setText(time);
  }
  
  public void bGnomeSort_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();
    
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    lStatusGnomeSort.setBackground(Color.green);
    lStatusGnomeSort.setText(time);
  }
  
    public void bBogoSort_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();
    bogosort(arrayBogoSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayInsertionSort) != true) {
      time = "mehr als 5s";
    }
    lStatusBogoSort.setBackground(Color.green);
    lStatusBogoSort.setText(time);
  }
  
    public void bCountingSort_ActionPerformed(ActionEvent evt) {
    long start = System.nanoTime();
    countingsort(arrayCountingSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayInsertionSort) != true) {
      time = "error";
    }
    lStatusCountingSort.setBackground(Color.green);
    lStatusCountingSort.setText(time);
  }
  
  public static void main(String[] args) {
    new Sortierung("Gotham Project - Lukas, Malou, Tobias und Felix");
  }
  
}
