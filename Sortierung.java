import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Lukas, Malou, Tobias, Felix
public class Sortierung extends Frame {
  
  Button bNewArray = new Button();
  Button bQuickSort = new Button();                                                                                                                                                                                                                                                                        
  Button bInsertionSort = new Button();                                                                                                                                                                                                                                                     
  Button bBubbleSort = new Button();
  Button bGnomeSort = new Button();    
  Button bBogoSort = new Button();  
  Button bStoogeSort = new Button();
  Button bSelectionSort = new Button();  
  Button bShakerSort = new Button();  
  Button bCountingSort = new Button();                                                                                                                                                                                                                                     
  Label lStatusQuickSort = new Label();
  Label lStatusGnomeSort = new Label();
  Label lStatusBubbleSort = new Label();
  Label lStatusInsertionSort = new Label();
  Label lStatusBogoSort = new Label();
  Label lStatusCountingSort = new Label();
  Label lStatusStoogeSort = new Label();
  Label lStatusShakerSort = new Label();
  Label lStatusSelectionSort = new Label();
  Label lQuickSort = new Label();
  Label lGnomeSort = new Label();
  Label lBubbleSort = new Label();
  Label lInsertionSort = new Label();
  Label lBogoSort = new Label();
  Label lStoogeSort = new Label();
  Label lSelectionSort = new Label();
  Label lShakerSort = new Label();
  Label lCountingSort = new Label();
  int[] array;
  Label lLaenge = new Label();
  NumberField nfArrayLength = new NumberField();
  List list = new List();
  
  Label lDauer = new Label();
  Choice cArrayTyp = new Choice();
  Label lArt = new Label();
  Checkbox cntcheck = new Checkbox("F\u00FCr CountingSort optimiert");
  
  //Countingsort braucht spezielle Arrays
  int readyforcountingsort = 0;
  
  //Hilfsfunktioen
  
  //Wandelt Nanosekunden um in Zeitangabe, z.B. 5036 -> "5,036us"
  String timestring(long time){
    if (time < 1000) {
      return Long.toString(time) + "ns";
    }
    else{
      long otime = time;
      long tim = otime > 999999 ? otime > 999999999 ? 1000000000 : 1000000 : 1000;
      String x = Long.toString(time / tim);
      if (time % tim > 0) {
        x += ".";
        while(time > 0){ 
          time%=tim;
          tim /= 10;        
          if(time > 0){
            x += time / tim;
          }
        }
      }
      
      return x + (otime > 999999 ? otime > 999999999 ? "s" :"ms" : "\u00B5s");
    }
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
  
  //GnomeSort Komplexitaet O(n^2)
  //Betrachtet 2 nebeneinanderliegende Zahlen, wenn noetig vertauscht diese und geht eins zurueck. Betrachtet erneut 2 Zahlen...
  //vertauscht eine Zahl so lange, bis die Zahl davor kleiner ist. Geht dann erneut die bereits sortierten Zahlen durch  
  void gnomeSort( int[] array ) { 
    for ( int index = 1; index < array.length; ) { 
      if ( array[index - 1] <= array[index] ) {     //Wenn die Zahlen richtig sortiert sind, geht man eins weiter
        ++index; 
      } else {                     
        int tempVal = array[index];                 //Vertauscht die groeßere und kleinere Zahl
        array[index] = array[index - 1]; 
        array[index - 1] = tempVal; 
        --index;                                    //geht zurueck, um die eben vertauschte Zahl mit der nun davor zu vergleichen
        if ( index == 0 ) {                         //wenn die Zahl nun die Vorderste im Array ist, bekommt diese den index 1
          index = 1; 
        }           
      } 
    } 
  }
  
  //"Schuettelt" das Array: Die Werte bleiben erhalten, bekommen aber eine neue Position.
  void schuetteln(int[] array){
    Random rn = new Random(); //Zufall erzeugen
    int len = array.length;
    for(int i = 0; i < len - 1; i++){
      int rnd = rn.nextInt(len - i); //Tausch mit zufaelligem Element
      int temp = array[i];
      array[i] = array[i + rnd];
      array[i + rnd] = temp;
    }
  }
  
  //Erzeugt ein Array der Laenge len und fuellt es mit zufaelligen Werten.
  int[] getRandomArray(int len){
    int[] array = new int[len];
    Random rn = new Random();
    for (int i = 0; i < len; i++) {
      array[i] = rn.nextInt();
    }
    return array;
  }
  
  //Erzeugt ein Array und fuellt es mit zufaelligen Werten <= 255.
  int[] getRandomArrayCnt(int len){
    int[] array = new int[len];
    Random rn = new Random();
    for (int i = 0; i < len; i++) {
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
  }
  
  //Implementiert Countingsort: Funktioniert in dieser Implementierung nur bei natuerlichen Zahlen <= 255. Zaehlt, wie oft jeder moegliche Wert vorkommt und schreibt dann jeden dieser Werte in Reihenfolge mit der jeweiligen Haeufigkeit zurueck in das Array. Komplexitaet O(n + 256)
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
    int i = links; 
    int j = rechts - 1;
    int pivot = array[rechts];       
    
    do {
      // naechstes Element von links, das groeßer als das Pivotelement ist, wird bestimmt
      while (array[i] <= pivot && i < rechts) {
        i = i + 1;
      }
      
      // naechstes Element von rechts, das kleiner als das Pivotelement ist, wird bestimmt
      while (array[j] >= pivot && j > links) {
        j = j - 1;
      }
      
      if (i < j) {   //wenn array[i] und array[j] in der falschen Reihenfolge stehen ([i] vor [j]), werden die beiden getauscht
        int x=array[i];
        array[i]=array[j];
        array[j]=x;
      }
    } while (i < j); // wird wiederholt bis i und j aneinander vorbeigelaufen sind 
    
    // array[i] (sollte groeßer als das Pivotelement sein, aber zur Sicherheit Ueberpruefung) wird mit dem Pivotelement array[rechts] getauscht
    if (array[i] > pivot) {
      int x=array[i];
      array[i]=array[rechts];
      array[rechts]=x;
    }
    
    // Position des Pivotelements wird zurueckgegeben   
    return i;
  }
  
  void quicksort(int[] array, int erstes, int letztes){ //erstes ist der Index des ersten Elements der (Teil-)Liste, letztes der Index des letzten. Komplexitaet O(n logn)
    if (erstes < letztes) {
      int mitte = teile(array, erstes, letztes);      //mitte ist der Index des Pivot-Elements nach der Sortierung durch teile()
      quicksort(array, erstes, mitte-1);        //linke Teilliste wird sortiert
      quicksort(array, mitte+1, letztes);         //rechte Teilliste wird sortiert
    }
  }
  
  //Der Selectionsort ist in seiner Grundform instabil und arbeitet in-place. Er basiert auf einem Bubblesort-Verfahren, tielt jedoch im Gegensatz zu
  //diesem das zu sortierende Array in einen unsortierten und einen bereits sortierten Teil auf. Hierdurch werden nicht zuvor bereits sortierte Werte
  //erneut verglichen, was eine Zeiteinsparung erwirkt.
  void selectionsort(int[] sortieren) {
    for (int i = 0; i < sortieren.length - 1; i++) {            //Es laufen zwei for schleifen um jeweils den derzeitigen Wert und den Wert einen Schritt weiter vergleichen zu koennen
      for (int j = i + 1; j < sortieren.length; j++) {
        if (sortieren[i] > sortieren[j]) {                      //Wenn der erste Wert kleiner ist als der folgende, wird dieser in eine temporaeren Variable abgespeichtert und durch den folgenden ersetzt
          int temp = sortieren[i];                              
          sortieren[i] = sortieren[j];
          sortieren[j] = temp;                                  //Der zweite Wert wird hier wieder mit den ersten ueberschrieben
        }
      }
    }
  }
  
  //Der Bubblesort ist ein stabiles Sortierverfahren, das in-place arbeitet. Es geht ein Array von Werten durch und vergleicht jeweils die zwei
  //naechsten Werte und vertauscht diese. Sobald das Array einmal durchlaufen wurde, beginnt es wieder von neuem, was es zu einem recht langsamen
  //Sortierverfahren macht. Komplexitaet O(n^2)
  public static void bubbleSort(int[] array) {
    
    boolean unsortiert=true;                                 //Hier wird der boolean unsortiert auf true gesetzt
    int localvar;                                            //Eine temporaere Variable wird erstellt
    
    while (unsortiert){                                      //Die while Schleife wird ausgefuehrt bis alle Eintraege sortiert sind
      unsortiert = false;                                    //unsortiert wird auf false gesetzt
      for (int i=0; i < array.length-1; i++) {                   //Die for-Schleife geht durch das Array
        
        if (array[i+1] < array[i]) {                                 //Hier wird ueberprueft, ob die folgende Zahl kleiner ist als die zurzeitige
          
          localvar   = array[i];                                 //Die Variablen werden getauscht
          array[i]       = array[i+1];
          
          array[i+1]     = localvar;
          unsortiert = true;  
        }
        
      }          
    } 
  }
  
  
  void shakerSort(int[] array) {
    int x=0; //Variable fuer Anzahl der aenderungen im Durchlauf
    int y=0; //Variable fuer Anzahl der Durchlaeufe
    int sortAnfang=0;
    int sortEnde=0; //Variablen fuer endgueltig positioniert Variablen am Anfang und Ende, nach jedem Durchlauf kommt bei der jeweiligen Variable 1 dazu, da die groeßte bzw. kleinste Zahl komplett durchgereicht wird
    
    do {
      x=0;     //Anzahl der aenderungen im Array zurueckgesetzt
      if (y%2==0) {        //Wenn die Anzahl der bisherigen Durchlaeufe gerade ist, wird das Array von links nach rechts durcchgegangen
        for (int i=sortAnfang; i<array.length-1-sortEnde; i++) {  //Array wird von links nach rechts durchlaufen. Paare werden ggf vertauscht wie bei BubbleSort
          if (array[i]>array[i+1]) {      //wenn array[i] und array[i+1] in der falschen Reihenfolge sind, werden sie getauscht
            int z=array[i];
            array[i]=array[i+1];
            array[i+1]=z;
            
            x+=1;                        //Anzahl der aenderungen im Durchlauf erhoeht
          } // end of if
        } // end of for
        sortEnde+=1;        //eine weitere Zahl am Ende ist endgueltig
      } // end of if
      else {          //Wenn die Anzahl der bisherigen Durchlaeufe ungerade ist, wird das Array von rechts nach links durcchgegangen
        for (int i=array.length-1-sortEnde; i>sortAnfang; i--) {
          if (array[i]<array[i-1]) {     //wenn array[i] und array[i+1] in der falschen Reihenfolge sind, werden sie getauscht
            int z=array[i];
            array[i]=array[i-1];
            array[i-1]=z;
            
            x+=1;                 //Anzahl der Aenderungen im Durchlauf erhoeht
          } // end of if
        } // end of for
        sortAnfang+=1;              //eine weitere Zahl am Anfang ist endgueltig
      } // end of if-else
      
    } while (x!=0); //Solange noch Aenderungen stattfinden (also die Reihenfolge nicht endgueltig ist), wird obiges wiederholt
  }
    //SoogeSort teilt das Array mehrmals in Drittel auf. Zuerst sortiert es die ersten zwei Drittel der Liste, diese Liste wird dann erneut gedrittelt.
    //Die letzten zwei Drittel werden sortiert, danach die ersten zwei Drittel. 
  public static void stoogeSort(int[] array, int i, int j) {
    if (array[j] < array[i]) {                  //Das lettzte und erste Element wird getauscht, falls das erste größer als das letzet ist.
      int tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
    }
    if (j - i > 1) {                         
      int t = (j - i + 1) / 3;               //Liste wird gedrittelt
      stoogeSort(array, i, j - t);
      stoogeSort(array, i + t, j);
      stoogeSort(array, i, j - t);
    }
  }
  
  //GUI
  Sortierung(String title) { 
    super(title);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
    });
    setSize(625, 625);  
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
        bInsertionSort_ActionPerformed(evt);
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
    
    bCountingSort.setBounds(368, 472, 75, 25);
    bCountingSort.setLabel("sortieren");
    bCountingSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCountingSort_ActionPerformed(evt);
      }
    });
    cp.add(bCountingSort);
    bStoogeSort.setBounds(368, 352, 75, 25);
    bStoogeSort.setLabel("sortieren");
    bStoogeSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bStoogeSort_ActionPerformed(evt);
      }
    });    
    cp.add(bShakerSort);
    bShakerSort.setBounds(368, 432, 75, 25);
    bShakerSort.setLabel("sortieren");
    bShakerSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bShakerSort_ActionPerformed(evt);
      }
    });
    cp.add(bStoogeSort);
    bSelectionSort.setBounds(368, 392, 75, 25);
    bSelectionSort.setLabel("sortieren");
    bSelectionSort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSelectionSort_ActionPerformed(evt);
      }
    });
    cp.add(bSelectionSort);
    
    
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
    lStatusStoogeSort.setBounds(240, 352, 110, 20);
    lStatusStoogeSort.setText("?");
    cp.add(lStatusStoogeSort);      
    lStatusSelectionSort.setBounds(240, 392, 110, 20);
    lStatusSelectionSort.setText("?");
    cp.add(lStatusSelectionSort);    
    lStatusShakerSort.setBounds(240, 432, 110, 20);
    lStatusShakerSort.setText("?");
    cp.add(lStatusShakerSort);        
    lStatusCountingSort.setBounds(240, 472, 110, 20);
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
    lStoogeSort.setBounds(112, 352, 110, 20);
    lStoogeSort.setText("StoogeSort");
    cp.add(lStoogeSort);
    lSelectionSort.setBounds(112, 392, 110, 20);
    lSelectionSort.setText("SelectionSort");
    cp.add(lSelectionSort);
    lShakerSort.setBounds(112, 432, 110, 20);
    lShakerSort.setText("ShakerSort");
    cp.add(lShakerSort);
    lCountingSort.setBounds(112, 472, 110, 20);
    lCountingSort.setText("CountingSort");
    cp.add(lCountingSort);
    
    
    lLaenge.setBounds(112, 40, 110, 20);
    lLaenge.setText("L\u00E4nge des Arrays");
    cp.add(lLaenge);
    nfArrayLength.setBounds(240, 40, 107, 20);
    nfArrayLength.setText("1000");
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
    bStoogeSort.setEnabled(false);
    bShakerSort.setEnabled(false);
    bSelectionSort.setEnabled(false);
    
    
    setVisible(true);
  }
  
  //Setzt die Oberfläche nach Erzeugung eines neuen Arrays zurück.
  public void resetLabels() {
    lStatusQuickSort.setBackground(Color.white);
    lStatusInsertionSort.setBackground(Color.white);
    lStatusBubbleSort.setBackground(Color.white);
    lStatusGnomeSort.setBackground(Color.white);
    lStatusBogoSort.setBackground(Color.white);
    lStatusCountingSort.setBackground(Color.white);
    lStatusShakerSort.setBackground(Color.white);
    lStatusStoogeSort.setBackground(Color.white);
    lStatusSelectionSort.setBackground(Color.white);
    
    lStatusQuickSort.setText("?");
    lStatusInsertionSort.setText("?");
    lStatusBubbleSort.setText("?");
    lStatusGnomeSort.setText("?");
    lStatusBogoSort.setText("?");
    lStatusCountingSort.setText("?");
    lStatusStoogeSort.setText("?");
    lStatusShakerSort.setText("?");
    lStatusSelectionSort.setText("?");
  }
  
  void turnArray(){               //Umdrehen durch Dreieckstaeusche
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
      length = 1000;
    }
    
    array = readyforcountingsort == 1 ? getRandomArrayCnt(length) : getRandomArray(length);
    
    if (cArrayTyp.getSelectedItem() != "zuf\u00E4llig") {     //wenn das Array sortiert sein soll, wird es sortiert.
      quicksort(array, 0, length - 1);
      
      if (cArrayTyp.getSelectedItem().equals("r\u00FCckw\u00E4rts sortiert")) {  //bei "rueckwaerts sortiert" wird das bereits sortierte Array umgedreht.
        turnArray();
      }
    } 
    
    resetLabels();
    
    bQuickSort.setEnabled(true);
    bBogoSort.setEnabled(true);
    bInsertionSort.setEnabled(true);
    bGnomeSort.setEnabled(true);
    bStoogeSort.setEnabled(true);
    bSelectionSort.setEnabled(true);
    bCountingSort.setEnabled(readyforcountingsort == 1 ? true : false);//Der Nutzer darf nur bei einem kompatiblen array countingsort einsetzen.
    bBubbleSort.setEnabled(true);
    bShakerSort.setEnabled(true);
    
    nfArrayLength.setBackground(Color.green);
  }
  
  public void bQuickSort_ActionPerformed(ActionEvent evt) { 
    int[] arrayQuickSort=array.clone();     //damit alle Sortierverfahren nacheinander ein Array sortieren koennen, wird das Array fuer jedes Verfahren dupliziert.
    long start = System.nanoTime();    //Startzeitpunkt ermitteln
    
    quicksort(arrayQuickSort, 0, arrayQuickSort.length - 1);
    
    long stop = System.nanoTime();   //Stopzeitpunkt ermitteln
    
    String time=timestring(stop - start);   //timestring() gibt Differenz zwischen stop und start als String aus 
    if (pruefen(arrayQuickSort) != true) {
      time = "error";
    }
    
    lStatusQuickSort.setBackground(Color.green);
    lStatusQuickSort.setText(time);  //Dauer anzeigen
  }
  
  public void bInsertionSort_ActionPerformed(ActionEvent evt) { 
    int[] arrayInsertionSort=array.clone();
    
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
    int[] arrayBubbleSort=array.clone();
    //arrayBubbleSort[5] = arrayBubbleSort[6] = 5;
    long start = System.nanoTime();
    
    bubbleSort(arrayBubbleSort);
    long stop = System.nanoTime();
    
    
    String time = timestring(stop - start); //wie oben
    if (pruefen(arrayBubbleSort) != true) {
      time = "error";
    }
    lStatusBubbleSort.setBackground(Color.green);
    lStatusBubbleSort.setText(time);
  }
  
  public void bGnomeSort_ActionPerformed(ActionEvent evt) {
    int[] arrayGnomeSort=array.clone(); 
    long start = System.nanoTime();
    gnomeSort(arrayGnomeSort);
    
    
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayGnomeSort) != true) {
      time = "error";
    }
    lStatusGnomeSort.setBackground(Color.green);
    lStatusGnomeSort.setText(time);
  }
  
  public void bBogoSort_ActionPerformed(ActionEvent evt) {  
    int[] arrayBogoSort=array.clone();
    long start = System.nanoTime();
    bogosort(arrayBogoSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayBogoSort) != true) {
      time = "mehr als 5s";
      lStatusBogoSort.setBackground(Color.red);
    }
    else{
      lStatusBogoSort.setBackground(Color.green);
    }
    lStatusBogoSort.setText(time);
  }
  
  public void bCountingSort_ActionPerformed(ActionEvent evt) {   
    int[] arrayCountingSort=array.clone();
    long start = System.nanoTime();
    countingsort(arrayCountingSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayCountingSort) != true) {
      time = "error";
    }
    lStatusCountingSort.setBackground(Color.green);
    lStatusCountingSort.setText(time);
  }
  
  public void bSelectionSort_ActionPerformed(ActionEvent evt) {   
    int[] arraySelectionSort=array.clone();
    long start = System.nanoTime();
    selectionsort(arraySelectionSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arraySelectionSort) != true) {
      time = "error";
    }
    lStatusSelectionSort.setBackground(Color.green);
    lStatusSelectionSort.setText(time);
  }
  
  public void bStoogeSort_ActionPerformed(ActionEvent evt) {   
    int[] arrayStoogeSort=array.clone();
    long start = System.nanoTime();
    stoogeSort(arrayStoogeSort, 0, arrayStoogeSort.length - 1);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayStoogeSort) != true) {
      time = "error";
    }
    lStatusStoogeSort.setBackground(Color.green);
    lStatusStoogeSort.setText(time);
  }
  
  public void bShakerSort_ActionPerformed(ActionEvent evt) {   
    int[] arrayShakerSort=array.clone();
    long start = System.nanoTime();
    shakerSort(arrayShakerSort);
    long stop = System.nanoTime();
    
    String time=timestring(stop - start);//wie oben
    if (pruefen(arrayShakerSort) != true) {
      time = "error";
    }
    lStatusShakerSort.setBackground(Color.green);
    lStatusShakerSort.setText(time);
  }
  
  public static void main(String[] args) {
    new Sortierung("Gotham Project - Lukas, Malou, Tobias und Felix");
  }
  
}
