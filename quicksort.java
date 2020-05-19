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
import java.lang.Thread;

public class quicksort extends PApplet {

  float[] values;
int w = 10;

int[] states;

int pcount = 0;


float[] results = new float[width];


public void quicksortn(float arr[],int start,int end) // the main quicksort function to recursively sort the numbers
{
  if(start>end){
     return;
  }
  int index = partition(arr, start, end);  // storing the returned pivotindex from partition
  states[index] = -1;   // regular white rectangles
  quicksortn(arr, start, index - 1);
  quicksortn(arr, index + 1, end);
}

public void swap(float arr[],int a,int b) //the swap function swaps the array values depending on the pivot index provided
 {
  float temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
  for(int i=0;i<values.length;i++)
  {
    results[i] = arr[i];
  }
  try{Thread.sleep(75);}catch(Exception e){}; //delay the thread operating this function
  redraw();
}

// using Lomuto partition
public int partition(float arr[],int start,int end) // the partition function to split the array and swap the values and to return the next pivotindex
{
  for(int i=start;i<end;i++)
  {
     states[i]=1;  // green rectangles represent the partition array
  }
  System.out.println("partition:"+pcount++);
  float pivotvalue = arr[end]; //pivot value is the end of array
  int pivotindex = start;      // start index for partition
  states[pivotindex] = 0;      // red rectangle is the pivot start index
  System.out.println("pivotvalue:"+pivotvalue);
  System.out.println("pivotindex:"+pivotindex);
  for(int i= start;i<end;i++)
  {
    if(arr[i]<pivotvalue) // control whether ascending or descending
    {
      swap(arr,i,pivotindex); // swap the arr[i] value with arr[pivotindex] value
      states[pivotindex]=-1;
      pivotindex++;
      states[pivotindex] = 0;
    }

  }
  swap(arr,pivotindex,end);
  
  for(int i=start;i<end;i++)
  {
    if(i!=pivotindex)
    {
      states[i] = -1;  // as the red rectangle goes through the partitioned array make the checked rectangles white
    }
  }
  return pivotindex;
}


  public void setup()  // generating the random values for the array according to the size of the window
  {

    values = new float[floor(width / w)];
    states = new int[width];
    for (int i = 0; i < values.length; i++) {
      values[i] = random(height);
      states[i] = -1;
//      System.out.println("the values generated:"+values[i]);
    }
    System.out.println("the number of values generated:" + values.length);
    t.start(); //start the thread after array is populated
  }


  // main thread control
  Thread t = new Thread(){
    public void run() {
        quicksortn(values, 0, values.length - 1);
        System.out.println("quicksort finished:");
        System.out.println("the sorted values are:");
        for(int i=0;i<values.length;i++)
        {
          System.out.println(""+results[i]);
        }
    }
  };



public void draw() // function to draw the rectangles on the window and visualize the sorting
{
  background(0);
  
  for(int i=0; i<values.length;i++)
  {
    noStroke();
    if(states[i] == 0)
    {
       fill(235, 73, 52); //red
    }
    else if(states[i] == 1)
    {
       fill(52, 235, 92); //green
    }
    else
    {
      fill(255);  //white
    }
    stroke(75);
    rect(i*w,height-values[i],w,values[i]);
  }

}

  public void settings() {
  size(800, 600);
 }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "quicksort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
