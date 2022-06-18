
// **
// This is a class for the MovingAverage objects. 
// */ 
public class MovingAverage{

	private static int min = 0;  //This field stores the smallest int value

	private static int max = 0;  //This field stores the largest int value

	private double sum;            //This field stores the sum

  private int high;              //This field stores the highest value of range

  private int low;               //This field stores the lowest value of a range

  private int nu;               //This field keeps count for fma

	private int numberOfItems;  //This field stores the count of items added to the object. 

	private int valueOfN = 0;   //This field stores the window size for calculating average of numbers in that window.

	private int[] storingNum;   //This array that stores the most recent N numbers added to the object.

	private int count = 0;       //Count is being used for adding and replacing the integers in the storingNum. 

	private static int numberOfItemsAdded = 0;   // This field stores the total number of items added in every object. 
	
	
	/** 
		This is the constructor for the moving average class 
 	*/

	public MovingAverage(){
		this.sum = 0;
		this.numberOfItems = 0;
	}
	
	/** 
		This is the second constructor for the moving average class 
 	*/
	public MovingAverage(int N){
		this.sum = 0;
		this.numberOfItems = 0;
		this.valueOfN = N;
		this.storingNum = new int[N]; 
		for(int i = 0; i < N;i++){
			storingNum[i] = 0;
		}				
	}
  /** 
		This is the third constructor for the moving average class 
 	*/
  public MovingAverage(int low, int high){
    this.low = low;
    this.high = high;
  }
	
	/** 
		A static method that returns the minimum int ever added to the object.
	*/
	public static int min(){
		return min;		
	} 
	
	/** 
		A static method that returns the maximum int ever added to the object.
	*/
	public static int max(){
		return max;		
	}
	
	/** 
		A method that first checks if the value is between low and high. If it is, it adds the given number to the object or 
		stores the most N recent numbers to the array: storingNum if a 
		window size is given. 
	*/
	
	public void add(int x){

    if(high > 0){
      if(x >= low && x <= high){
        this.sum = sum + x;
        nu = nu + 1;
      }
    }

    else { 
      if (this.valueOfN == 0) 
      {
			  this.sum = sum + x;	
		  }
	  	else{
			  if (this.count == this.valueOfN){
				  this.count = 0;
			  }
			  storingNum[this.count] = x;
			  this.count = this.count+1; 
		}
  }

		this.numberOfItems = numberOfItems + 1;  

		MovingAverage.numberOfItemsAdded = MovingAverage.numberOfItemsAdded + 1; 

    if(high == 0){
      if((this.numberOfItems==1)&&(MovingAverage.numberOfItemsAdded==1)){
        max = x; 
        min = x;
      } 
      else if(x > max){
        max = x;
      } 
      else if(x < min){
        min = x;
      }
    }
    else{
      if(x >= low && x <= high){
        if((this.numberOfItems==1)&&(MovingAverage.numberOfItemsAdded==1)){
        max = x; 
        min = x;
      } 
      else if(x > max){
        max = x;
      } 
      else if(x < min){
        min = x;
      }
      }
    }
  }
	

	/** 
		A method that returns the average of all the numbers
    in the object or the most recent N numbers if the window size is given. 
	*/
	
	public double avg(){
		double average;

    if(this.numberOfItems == 0 && high == 0 && low == 0){
			average = 0.0;
		}
		else if (this.valueOfN == 0 && high == 0 && low == 0) {
			average = (this.sum)/this.numberOfItems;	
		}

    else if(high > 0){
      if(nu == 0){
          average = 0.0;
      }
      else{
        average = (this.sum)/nu;
        return average;
      }
    }

		else{
			this.sum = 0;
			for(int i =0; i<this.valueOfN;i++){
				this.sum = this.sum + storingNum[i]; 	
      }
			if(this.numberOfItems>=this.valueOfN){
				average = (this.sum)/this.valueOfN;
			} else {
				average = (this.sum)/this.numberOfItems;	
			}
					
		}
    return average;
    }	
	}
  
