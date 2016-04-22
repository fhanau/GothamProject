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
} 
