//Felix Hanau
public class BinBaum {
  int wurzel;
  BinBaum right;
  BinBaum left;
  
  
  void insert(int val){
    if (this.wurzel == -1){
      this.wurzel = val;
    }
    else{
      if (val < this.wurzel) {
        if (this.left == null) {
          this.left = new BinBaum();
        }
        this.left.insert(val);
      }
      else{
        if (this.right == null) {
          this.right = new BinBaum();
        }
        this.right.insert(val);
      }
      
    }
  }
  
  void print(){
    if (this.left != null) {
      this.left.print();
    }
    System.out.print(this.wurzel + " ");
    if (this.right != null) {
      this.right.print();
    }
  }
  int printtoarray(int[] array, int position){
    if (this.left != null) {
      position = this.left.printtoarray(array, position);
    }
    array[position] = this.wurzel;
    position++;
    if (this.right != null) {
      position = this.right.printtoarray(array, position);
    }
    return position;
  }
  
  BinBaum(){this.wurzel = -1;}
  public static void main(String[] args) {
    
    int [] array = {5, 6, 91, 31, 7, 3, 900, 8, 1, 9, 3575, 5};
    BinBaum bin = new BinBaum();
    for(int i = 0; i < 12; i++){
      bin.insert(array[i]);
    }
    System.out.println(array.length + " Elemente:");
    bin.print();
  }
  
} 
