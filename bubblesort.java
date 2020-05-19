import processing.core.*; 


public class bubblesort extends PApplet {

float[] values;

float[] results = new float[width];

int i = 0;
int j = 0;
int w = 10;

public void setup() // generating the random values for the array according to the size of the window
 {
  //fullScreen(P2D);
  
  values = new float[width/w];
  for (int i = 0; i < values.length; i++) {
    values[i] = random(height);
    //values[i] = noise(i/100.0)*height;
  }


  //for (int i = 0; i < values.length; i++) {
  //  }
  //}
}

public void draw() // function to draw the rectangles on the window and perform and visualize the sorting
 {
  background(0);

  if (i < values.length) {
    for (int j = 0; j < values.length-i-1; j++) {
      float a = values[j];
      float b = values[j+1];
      if (a > b) {
        swap(values, j, j+1);
      }
    }
  } else {
    println("finished");
    println("the sorted array is:");
    for (int i=0;i<values.length;i++)
    {
      System.out.println(""+results[i]);
    }
    noLoop();
  }
  i++;

  for (int i = 0; i < values.length; i++) {
    stroke(75);
//    line(i, height, i, height - values[i]);
    rect(i*w,height-values[i],w,values[i]);
  }
}

public void swap(float[] arr, int a, int b)  //the swap function swaps the array values
  {
  float temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
  for(int i=0;i<values.length;i++)
  {
    results[i] = arr[i];
  }
  try{Thread.sleep(5);}catch(Exception e){}; //delay the thread operating this function
  redraw();

}
  public void settings()
  {
    size(800, 600);
  }

  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "bubblesort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
