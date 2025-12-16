
public class test {
    
    public void printAnswer (int userNum, boolean neg){
        int answer[], longAnswer[];

        if (neg == false){
            System.out.println("Number provided: " + userNum);
            System.out.println();
            answer = convertToBin(userNum);
            longAnswer = sixteenBitRep(answer);

            System.out.print("Number in binary: ");
            printArray(answer);
            System.out.println();

            System.out.println("Number of bits: " + answer.length);
            System.out.println();

            System.out.print("Here is 16 bit rep: " );
            printArray(longAnswer);
            System.out.println();

            System.out.print("Here 1's complement rep: " );
            printArray(longAnswer);
            System.out.println();

            System.out.print("Here is 2's complement rep: ");
            printArray(longAnswer);
            System.out.println();

            printNums(userNum);

        }
        else{

            // userNum is negative, newNum is its positive value

            System.out.println("Number provided: " + userNum);
            System.out.println();
            int newNum = userNum * - 1 ;


            answer = convertToBin(newNum);
            longAnswer = sixteenBitRep(answer);

            System.out.print("Number in binary: -");
            printArray(answer);
            System.out.println();

            System.out.println("Number of bits: " + answer.length);
            System.out.println();

            System.out.print("Here is 16 bit rep: -" );
            printArray(longAnswer);
            System.out.println();

            System.out.print("Here 1's complement rep: " );
            int[] newLong = complement(longAnswer);
            printArray(newLong);
            System.out.println();

            System.out.print("Here is 2's complement rep: ");
            int[] twosLong = twosComp(longAnswer);
            printArray(twosLong);
            System.out.println();

        printNums(newNum);

        }

    }

    public static void printNums(int dividend){
        System.out.println("Here is the operation visualized for your learning:");

        //print initial values
        System.out.println("  ----");
        System.out.println("2 | " + dividend);

        do {
            int remainder = dividend % 2;
            dividend = dividend / 2;
            System.out.println("  ----");
            System.out.println("2 | " + dividend + " R " + remainder);
            
        } 
        while(dividend > 0 );

        System.out.println();
        System.out.println("Notice that the various remainders match the number in binary going from the bottom to the top");
        System.out.println();
    
    }

    public static int[] convertToBin(int number){

        int dividend = number;         
        int remainder;
        int answer[];
        double log2n = (Math.log10(number) / Math.log10(2));
        int arraySize = (int) Math.ceil(log2n);

        if(dividend == 0){
            answer = new int[1];
            answer[0] = 0;
        }

        else{
            if (log2n % 1 == 0)                    
            {
                int[] newArray = new int[arraySize + 1 ];
                newArray[0] = 1;
                for(int i = 1; i < newArray.length; i++){
                    newArray[i] = 0;
                }
                answer = newArray;
            }
            else
            {
                int[] binArray = new int[arraySize];
                int i = arraySize - 1; 
                while (i>=0)
                {
                    remainder = dividend % 2;          
                    binArray[i] = remainder;
                    --i;
                    dividend = dividend / 2;      
                }

                answer = binArray;        
            }
        }
    
        return answer;
    }


    public static int[] sixteenBitRep(int[] bin){
        
        int[] stbr = new int[16];
        int padding = 16 - bin.length;

        for (int i = 0; i < padding; i++){
            stbr[i] = 0;
        }
        
        for (int i = 0; i < bin.length; i++){
            stbr[padding + i] = bin[i];
        }
        
        return stbr;
    }


    //not used
    public static int BinToInt(int[] binArray){
        int result = 0;

        for (int bit : binArray){
            result = result * 10 + bit;
        }
        
        return result;
    }


    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print( arr[i]);
        }
        System.out.println();
    }

    // complements the binary array
    public static int[] complement(int[] bin){
        int[] compArr = new int[bin.length];

        for (int i = 0; i < bin.length; i++)
        {
            if (bin[i] == 1)
                compArr[i] = 0;
            
            else
                compArr[i] = 1;
        }
        
        return compArr;
    }

    public static int[] twosComp(int[] bin){
        int[] localArr = complement(bin);
        int[] finalArr = addOne(localArr);

        return finalArr;

    }

    //binary addition of 1 
    public static int[] addOne(int[] binArr){
        int carry = 0;
        int[] finalAns = new int[binArr.length];
        
        int[] oneVal = new int[binArr.length];

        //intitialize array with same length which only contains 1
        for (int i = 0; i < oneVal.length - 2; i++){
            oneVal[i] = 0;
        }
        oneVal[oneVal.length - 1] = 1;


        //then add the two arrays
        for(int i = binArr.length - 1; i >= 0; i--){
            int[] temp = bitAdder(binArr[i], oneVal[i], carry);
            carry = temp[1];
            finalAns[i] = temp[0];
        }
  
        return finalAns;
    }

    public static int[] bitAdder (int num1, int num2, int carry){
        //format for ans[]
        // ans[0] = answer 
        // ans[1] = carry

        int[] ans = new int[2];

        if (num1 + num2 + carry == 0 ){
            ans[0] = 0;
            ans[1] = 0;
        } 
        else if (num1 + num2 + carry == 1){
            ans[0] = 1;
            ans[1] = 0;
        }
        else if (num1 + num2 + carry == 2){
            ans[0] = 0;
            ans[1] = 1;
        }
        else{
            ans[0] = 1;
            ans[1] = 1;
        }
        
        return ans;

    }
}