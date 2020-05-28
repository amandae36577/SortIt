//Amanda Esposito
// This program will use a merge sort like algorithm to sort input from files or the command line.

import java.io.*;//insertion of the needed java librarys
import java.io.BufferedReader;

public class SortIt
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader inputFile = null; //most of my variables
        InputStreamReader stdin = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(stdin);
        int count = 0;
        LinkedList One = new LinkedList(); //my lists used
        LinkedList Two = new LinkedList();
        LinkedList Curr;
        Curr = One;
        String dataline = ""; // hold data values temporarily
        Boolean reverse = false; //shows if the list should be reversed
        String oneData = "";
        String twoData = "";
        int compare;
        int count1 = 0;
        int count2 = 0;
        Boolean CL = true;
        Boolean valid = true;
        String S2 = "-";
        String S3 = "-r";
        if(args.length == 0) //if no arguements then return
            return;
        while(args.length > count)
        {
            String fname = args[count]; //goes through the different arguments
            if(fname.equals(S2))
            {
                while(CL == true) //takes in command line strings
                {
                    dataline = keyboard.readLine();
                    if(dataline == null)
                        CL = false;
                    else
                    {
                        if (Curr == One)
                            compare = dataline.compareTo(oneData);
                        else
                            compare = dataline.compareTo(twoData);
                        if(compare >= 0)
                        {
                            Curr.enqueue(Curr, dataline);//add data then look at the next piece
                            if(Curr == One) //figure out which list is Curr
                                oneData = dataline;
                            else
                            {
                                twoData = dataline;
                            }
                        }
                        else
                        {
                            if(Curr == One) //change lists
                                Curr = Two;
                            else
                                Curr = One;
                            Curr.enqueue(Curr, dataline);
                        }
                    }
                }
                count = count+1; //increase argument count
            }
            else if (fname.equals(S3)) //reverse check
            {
                reverse = true;
                count = count + 1;
            }
            else
            { //files used
                try
                {
                    FileReader freader = new FileReader(fname);
                    inputFile = new BufferedReader(freader);
                    valid = true;
                    count = count + 1; //increase argument count
                }
                catch (IOException e) // reports a problem with opening a file
                {
                    System.err.println("Error: Unable to open file: " + fname);
                    System.err.println("Can not recover from error.  Exiting.");
                    count = count + 1;
                    valid = false;
                }
                if(valid == true)
                {
                    dataline = inputFile.readLine();
                    if(dataline != null)
                    {
                        oneData = "";
                        twoData = "";
                        compare = dataline.compareTo(oneData);
                        while(dataline != null) //while more to read in
                        {
                            while(compare >= 0)
                            {
                                Curr.enqueue(Curr, dataline);//add data then look at the next piece
                                if(Curr == One)
                                    oneData = dataline;
                                else
                                    twoData = dataline;
                                dataline = inputFile.readLine();
                                if(dataline != null)
                                {
                                    if(Curr == One)
                                        compare = dataline.compareTo(oneData);
                                    else
                                        compare = dataline.compareTo(twoData);
                                }
                                else
                                    compare = -1;
                            }
                            if(Curr == One) //Changing Current list being used
                            {
                                Curr = Two;
                            }
                            else
                                Curr = One;
                            if(dataline != null)
                                compare = 0; //makes sure the list gets switched
                        }
                        inputFile.close();
                    }
                }
            }
        }
        //All of the data is inserted
        String hold2;
        String hold1;
        int backCompare = 0;
        if(One.getFirst(One) == null) //prevents a sorted list from creating a nullPointerEX
        {
            One.dequeue(One, 0);
        }
        if(Two.getFirst(Two) == null)
        {
            Two.dequeue(Two, 0);
        }
        while((Two.length() > 0 && One.length() > 0))// confirm == false) //while it is not all in one list
        {
            Boolean finished = false;
            count1 = One.length();
            count2 = Two.length();
            hold1 = One.getFirst(One);
            hold2 = Two.getFirst(Two);
            Curr = One;
            Boolean OneFirst = true;
            Boolean TwoFirst = true;
            while(count1 > 0 || count2 > 0)
            //while the current lists have not been fully traversed
            {
                if(count1 > 0 && count2 > 0) //base cases
                {
                    compare = hold1.compareTo(hold2);
                    //if hold1 less than hold 2;
                    if(compare <= 0)
                    {
                        dataline = hold1;
                    }
                    else
                        dataline = hold2;
                }
                else if (count1 == 0 && count2 > 0)
                {
                    dataline = hold2;
                }
                else if (count2 == 0 && count1 > 0)
                {
                    dataline = hold1;
                }
                if(OneFirst == true)//makes the transition from one list to another smooth
                {
                    backCompare = 1;
                }
                else if(TwoFirst == true)//makes the transition from one list to another smooth
                {
                    backCompare = 1;
                }
                else
                {
                    backCompare = dataline.compareTo(Curr.getLast(Curr)); //comapre data to the last element
                    if(backCompare < 0 && count2 > 0 && count1 > 0)
                    {
                        if(dataline == hold1)
                            dataline = hold2;
                        else
                            dataline = hold1;
                        backCompare = dataline.compareTo(Curr.getLast(Curr));
                    }
                }
                while(backCompare >= 0 && finished == false) //loop until list is changed
                {
                    Curr.enqueue(Curr, dataline);//add data then look at the next piece
                    if(dataline == hold1)
                    {
                        count1 = count1-1;
                        One.dequeue(One, 0);
                        if(count1 > 0)
                        {
                            hold1 = One.getFirst(One);
                        }
                    }
                    else
                    {
                        count2 = count2-1;
                        Two.dequeue(Two, 0);
                        if(count2 > 0)
                        {
                            hold2 = Two.getFirst(Two);
                        }
                    }
                    //decrease One if it's node was used
                    if(count1 > 0 && count2 > 0)
                    {
                        compare = hold1.compareTo(hold2);
                        //if hold1 less than hold 2;
                        if(compare <= 0) //find smaller value
                        {
                            dataline = hold1;
                        }
                        else
                            dataline = hold2;
                        backCompare = dataline.compareTo(Curr.getLast(Curr));
                        if(backCompare < 0)//makes sure other value is not
                        {//greater then last in current linked list
                            if(dataline == hold1)
                                dataline = hold2;
                            else
                                dataline = hold1;
                            backCompare = dataline.compareTo(Curr.getLast(Curr));
                        }
                    }
                    else if(count1 == 0 && count2 > 0)
                    {
                        backCompare = 1;
                        if(count1 == 0 && count2 > 0 && backCompare >= 0)
                        { //adds values when count1 runs out
                            hold2 = Two.getFirst(Two);
                            dataline = hold2;
                            backCompare = dataline.compareTo(Curr.getLast(Curr));
                        }
                    }
                    else if(count2 == 0 && count1 > 0)
                    { //adds values from One when count2 runs out
                        backCompare = 1;
                        if(count2 == 0 && count1 > 0 && backCompare >= 0)
                        {
                            hold1 = One.getFirst(One);
                            dataline = hold1;
                            backCompare = dataline.compareTo(Curr.getLast(Curr));
                        }
                    }
                    else if (count1 == 0 && count2 == 0)
                    {
                        backCompare = -1;
                        finished = true;
                    }
                }
                if(backCompare < 0 && finished == false && count2 > 0 && count1 > 0)
                {   //changes from one list to the other and keeps hold as it should be, the lesser value
                    if(dataline == hold1)
                        dataline = hold2;
                    else
                        dataline = hold1;
                }
                else if (count1 == 0 && count2 > 0)
                {
                    dataline = hold2;
                }
                else if (count2 == 0 && count1 > 0)
                {
                    dataline = hold1;
                }
                backCompare = 1;
                if(Curr == One) //changes current
                {
                    Curr = Two;
                }
                else
                    Curr = One;
                OneFirst = true; //resets the first variables
                TwoFirst = true;
            }
            if(One.getFirst(One) == null) //prevents null pointer when a completely sorted file is entered
            {
                One.dequeue(One, 0);
            }
            if(Two.getFirst(Two) == null)
            {
                Two.dequeue(Two, 0);
            }
        }
        if(reverse == true) //reversing final linked list
        {
            if(One.length() > 0)
            {
                One.reverse(One);
            }
            else
            {
                Two.reverse(Two);
            }
        }
        if(One.length() > 0) //printing final list
        {
            One.printList(One);
        }
        else
        {
            Two.printList(Two);
        }
    }
}