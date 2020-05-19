import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class quicksort2 extends PApplet {

float[] values;

public void swap(float[] arr, int a, int b) {
  float temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
  try{Thread.sleep(10);}catch(Exception e){}; //delay the thread operating this function
  redraw();
  
}

public int partition(float[] arr, int start, int end) {
  int pivotIndex = start;
  float pivotValue = arr[end];
  for (int i =start; i < end; i++) {
    if (arr[i] < pivotValue) {
      swap(arr, i, pivotIndex++);
      // pivotIndex++;
    }
  }
  swap(arr, pivotIndex, end);
  return pivotIndex;
}

public void quickSort(float[] arr, int start, int end) {
  if (start < end) {
    int index = partition(arr, start, end);
    quickSort(arr, start, index - 1);
    quickSort(arr, index + 1, end);
  }
}

//make a thread and override the run to perform the sort instead
Thread t = new Thread(){
  public void run(){
    quickSort(values, 0, values.length - 1);
  }
};

public void setup() {
  
  values = new float[width];
  for (int i = 0; i < values.length; i++) {
    values[i] = random(0, height);
  }
  t.start(); //start the thread after array is populated
}

public void draw() {
  background(0);
  for (int i = 0; i < values.length; i++) {
    stroke(255);
    line(i, height, i, height - values[i]);
  }
  //remove this quicksort call here bc its now done in the thread - in parralel to the sketch.
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "quicksort2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
