/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M Hamza Khan
 */

import java.util.MissingFormatArgumentException;
import java.io.*;
import java.util.Scanner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import java.util.InputMismatchException;

public class DG_Filing {

    /**
     * @param args the command line arguments
     */
    
    // IT IS MAIN FUNCTION IN WHICH ALL PROJECT WILL BE PERFORM DIFFERENT FUNCTIONS THAT IS ADDIGN RECORD VIEW RECORD SEARCH UPDATING AND GENERATING DATE SHEET
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException  {
            // TODO code application logic here
        Scanner input = new Scanner(System.in);
        while(true) {
        System.out.println("\n\n1.ADD RECORD\n2.VIEW RECORD\n3.SEARCH RECORD\n4.UPDATE RECORD\n5.Generate Time Table\n6.EXIT\n\nPlease Chooose an Option: ");
        int a = input.nextInt();
        //IT WILL ADDS RECORD OF ALL DATESHEET CONTENTS THAT IS CLASSSES ROOMS SUBJECTS   
        if(a==1) {                  //ADDING RECORD
            add_record();  
        }
        //IT WILL VIEW RECORD OF ADDED CONTENTS AS LIKE CLASSES AND SUBJECTS, ROOM
        if(a==2) {               //VIEWING RECORD
            ViewRecord();
        }
        //IT WILL SEARCH REOCRD OF DATE SHEET CONTENTS WHICH MAY BE ROOMS,CLASSES, SUBJECTS
        if(a==3) {
            Searching();
        }
        //IT WILL UPDATE RECORD OF DATE SHEET CONTENTS
        if(a==4) {
            updateRecord();          //UPDATING RECORD
        }
        //IT WILL GENERATES TIMETABLE BY TAKING PERFORMING ALL ABOVE FUNCTION AND TAKING DATA FROM ABOVE FUNCTION AND THEN AFTER ALL IT WILL GENERATES DATESHEET
        if(a==5) {
            System.out.println();    //GENERATING TIMETABLE
            TimeTable();
            
        }
        //THIS IS EXIT FUNCTION AND IT WILL EXITS OUR PROGRAM AND ON EXIT IT WILL ALSO DELETES ALL THE RECORD FROM FILES
        if(a==6) {  
            //DELETING FILES BYTES
            DeletingFileContents();
        //IT WILL EXITS OUR PROGRAM
            System.exit(0);
        }
        
        }
    }
    //THIS FUNCTION WILL ADDS RECORD BY THE THE HELP OF OTHER FUNCTIONS
    public static void add_record() throws IOException { 
         TotalClasses();//ADDS TOTAL CLASSES
         SubjectsRecord();//ADDS TOTAL SUBJECTS
         RoomsRecord();//ADDS ROOMS RECORD
         
    }
    //GIVING TOTAL CLASSES FROM USER
    public static void TotalClasses() throws IOException{
        
        
        //IT WILL CREATS FILE BY NAME TOTALClasses and the record of total classes number will be saved in it
        
        
        //ITS CREATING A BINARY FILE OF SAVING CLASSSES RECORD IN IT 
        try (
            FileOutputStream output = new FileOutputStream("TotalClasses.java");
                )   
        {
            //ITS A TRY FOR CATCHING EXCEPTIONS FROM THAT WILL OCCUR IN THE PROGRAM
            try{
                //SCANNNER FUNCTION FOR TAKING INPUTS FROM USER
        Scanner input = new Scanner(System.in);
        
        //TAKES NUMBER OF CLASSES FROM A USER
        System.out.print("Enter number of Classes: ");
        int TotalClasses = input.nextInt();
        //IT WILL CHECKS VALID INPUT FROM A USER IF IT WILL NOT VALID THEN IT WILL RERUN AND TAKES VALID INPUT
        boolean a =false;   //BOOLEAN FUNCTION FOR CHECKING VALID INPUT 
        if(!a) { //CONDITION FOR RUNNING PROGRAMS
            a=true;
            output.write(TotalClasses);
        }
        //CONDITION FOR CHECHKING VALID INPUTS
        if(TotalClasses<=0) {
              a=false; //IT INPUT WILL BE INVALID
              System.out.print("Invalid input!\n");  
              TotalClasses();//RECALLING FUNCTION FOR INPUT OF CLASSES
            
        
            }
            }
            //THIS WILL CATCHS EXCEPTION THAT WILL OCCUR IN THE PROGRAM
            catch(InputMismatchException ex) {//CATCHING INVALID INPUT EXC...
                System.out.print("InputMismatchException occured\n" );
                TotalClasses();
                
            }
            //IT WILL CHECKS EXCEPTION IF THERE ARE CHANCES/WILL OCCUR
            catch(IllegalArgumentException ex) {
                System.out.print("Invalid Number\n");
                TotalClasses();//RECALLING A FUNCTION 
            }
                
        }
  
    
        
    }
    //ADDING SUBJECTS RECORD FOR EVERY CLASS 
    public static String[][] SubjectsRecord() throws FileNotFoundException, IOException {
        
        //IT WILL READS DATA FROM FILE NAMED TOTALCLASSES FOR PROPER USE
         try (
              FileInputStream input = new FileInputStream("TotalClasses.java");//READING DATA FROM A FILE
               ) {
             try{
            int a=input.read();//INPUT NAMES a
         
            String[][] SubjectsNames = new String[a][];//STRING ARRAY
            Scanner abc = new Scanner(System.in);
            String[] Classes = new String[a];
        for(int i=0;i<Classes.length;i++) {        //CONDTION FOR TAKING CLASSES NAME FOR ALL CLASSES
        System.out.print("Enter Class "+i+" name: ");
        Classes[i] = abc.next();
        }
         for (int i=0; i<a; i++) { //CONDITION
             //TAKING TOTAL  SUBJECTS OF EVERY CLASS
             System.out.print("Enter Class "+Classes[i]+" Total Subjects: ");
             SubjectsNames[i] = new String[abc.nextInt()];//STORING CLASSES TOTAL SUBJECTS IN ARRAY NAMED SubjectsNames
         }
        for(int i=0;i<SubjectsNames.length;i++) {//conditon for classes subjects
            for(int j=0;j<SubjectsNames[i].length;j++) { //TAKING SUBJECTS NAMES OF EVERY CLASS
            System.out.print("Enter Class "+Classes[i]+" Subject "+j+": ");
            SubjectsNames[i][j]= abc.next();
        
    }
             
        }
            //WRITING DATA TO FILE
             try ( //it will writes data to a created file
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SubjectsRecord.java", true));
            ) {
                
            output.writeObject(Classes);//writing classes data in a file
            output.writeObject(SubjectsNames);//writng subjects data in a file
            return SubjectsNames; 
} 
    }
         
            //it catchs Exception 
        catch(InputMismatchException ex) {
            System.out.print("InputMismatchException occurred\n");
            SubjectsRecord();//recalling a function
        }
            
        
       return null;
    }
    }
    //TAKING ROOMS RECORD
    public static String[] RoomsRecord() throws FileNotFoundException, IOException {
        try{//try for throwing exceptions
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Total Rooms:");
        int TotalRooms = input.nextInt();
        
        //it a string array
        String[] Rooms = new String[TotalRooms];
        
        for(int i=0;i<Rooms.length;i++) { //TAKING ROOM NAMES
            System.out.print("Enter Room "+i+" Name: ");
            Rooms[i] = input.next();
        }
        
       
    //it will creates new binary file named RoomsRecord
        
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("RoomsRecord.java", true));
            ) {
            output.writeObject(TotalRooms);//writng rooms record to a file
            output.writeObject(Rooms);//wRITING DATA TO A GIVEN FILE
        
    }
        
         
    return Rooms; //RETURNING ROOMS
        }
    catch(InputMismatchException ex) { //CATCH EXEC..
        System.out.print("Invalid Input\n");
        RoomsRecord();//RECALLING
            
        }
    
    return null;
    }
    //VIEWING RECORD OF ALL ENTERED DATA
    public static void ViewRecord() throws IOException, FileNotFoundException, ClassNotFoundException {
        try { //IT WILL THROW EXCEPTIONS...
        System.out.println("CLASSES NAME");
        ClassesView();
        System.out.println("ROOMS");       
        RoomView();
                        
        System.out.println();                
        SubjectsView();            
                        
        
        }
        catch(EOFException ex) { //IT WILL CATCHES EXCEPTIONS...
            System.out.print("No record Found\n\n");
            
        }
        catch(MissingFormatArgumentException ex) { //EXCEPT...      
    }
    }

                        
        
    //CLASSES VIEWING FOR DATA
    public static String[] ClassesView() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        //IT WILL READS DATA FROM FILE
        try (ObjectInputStream input =new ObjectInputStream(new FileInputStream("SubjectsRecord.java"));
                ) {
            try { //IT WILL THROWS EXCEPTIONS.......
            String[] Classes = (String[])(input.readObject());
             for(int i=0;i<Classes.length;i++) {  //DISPLAYING CLASSES
                 System.out.println(Classes[i]+" ");
             }
             System.out.println();
             return Classes;
            } 
            catch(ClassCastException ex) { //IT WILL CATCHES EXCEPTION....
            
        }
            return null;
        }
    }
    //METHOD FOR VIEWNG RECORD OF SUBJECTS AMD CLASSES ETC......................
    public static String[][] SubjectsView() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        //IT WILL TAKE DATA FROM  FILE AS WE SAY READING A DATA FROM FILE
        try (ObjectInputStream input =new ObjectInputStream(new FileInputStream("SubjectsRecord.java"));
                ) {
            try {
            String[] Classes = (String[])(input.readObject()); //ARRAY OF STRINGS FOR CLASSES
            String[][] SubjectsNames1 = (String[][])(input.readObject());//ARRAY OF STRINGS FOR SUBJECTS
            
            //DISPLAYING SUBJECTS NAMES
            for(int i=0;i<SubjectsNames1.length;i++) { //IT WILL PRINTS SUBJECTS NAMES
                for(int j=0;j<SubjectsNames1[i].length;j++) {
                    System.out.print(SubjectsNames1[i][j]+" ");     
                }
               System.out.println(); 
          
            }    
       return SubjectsNames1;
            }
            catch(ClassCastException ex){ // THIS IS CATCHING AN EXCEPTION
        }
        
        
            
    }return null;
    }
    
    //THIS METHOD IS FOR VIEING DATA OF ROOMS WHICH ARE SAVED IN A  BINARY FILE
    public static String[] RoomView() throws FileNotFoundException, IOException, ClassNotFoundException {
        //READING DATA FROM  FILE
        try (ObjectInputStream input =new ObjectInputStream(new FileInputStream("RoomsRecord.java"));
                ) {
            
            //IT IS READING OR TAKING DATA FROM ABOVE FILE
            int room = (int)(input.readObject());
            String[] Roomss = (String[])(input.readObject()); // ARRAY OF ROOMS
            for(int i=0;i<Roomss.length;i++) { //DISPLAYING ROOMS
                 System.out.println(Roomss[i]+" "); //PRINTING ROOMS
             }
             
            
        
        return Roomss; //IT IS RETURNING ROOMS
        }      
    }
    //UPDATING RECORD
    //THIS MEHTOD IS FOR ALL REOCRD UPDATING WHICH IS PRESENT IN DIFFERENT FILES
    public static void updateRecord() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        
        try { // IT WILL THROWW EXE...
        Scanner input = new Scanner(System.in);
        System.out.print("1.Edit Record\n2.Delete All Record\n3.Back\n\nPlease Choose Any Option: "); 
        int a = input.nextInt(); //VARIABLE NAME A FOR STORING INPUT
        
        if(a==1) { //CONDIOTIONG
            //IT IS updating classes RECORD WHICH IS PRESENT IN FILES
            System.out.print("What do you want to edit\n1.Total Classes\n2.Total Rooms\n3.Casses Names & Subjects\nPlease Choose Option: ");
            int b=input.nextInt();
            if(b==1) { 
                //IT IS ACCESSIGN FILES OF UPDATING DATA
                File Classess = new File("TotalClasses.java");
                
                //IT IS ACCESSING DATA FROM FILE...........
                RandomAccessFile put = new RandomAccessFile(Classess, "rw");
                put.setLength(0); //IT WILL DELETES DATA OF FILE
                TotalClasses(); //RECALLING A FUNC...
               File Subjects = new File("SubjectsRecord.java");
              //IT IS ACCESSING DATA FROM FILE.......
               RandomAccessFile pt = new RandomAccessFile(Subjects, "rw");
               pt.setLength(0);//IT WILL DELETES DATA OF FILE
                SubjectsRecord();//RECALLING A FUNC...
                
            }
            if(b==2) {
                //IT IS ACCESSING DATA FROM FILE...
                File Room = new File("RoomsRecord.java");
   
             RandomAccessFile ptt = new RandomAccessFile(Room, "rw");
             ptt.setLength(0);//IT WILL DELETES DATA OF FILE
             RoomsRecord();//RECALLING A FUNC..
                
            }
            if(b==3) {
                File Subjects = new File("SubjectsRecord.java");
    
               RandomAccessFile pt = new RandomAccessFile(Subjects, "rw");
               pt.setLength(0);
               SubjectsRecord();
               
            }
        }
        
        
        //IT IS FOR DELETING ALL  DATA FROM ALL FILES
        if(a==2) {
            System.out.print("\n1.Yes\n2.No\n\nAre you Confirm?");//Taking confirmation from user of deleting data from files
            int b=input.nextInt();
            if(b==1) {
              DeletingFileContents(); 
              System.out.print("Done\n");
              updateRecord();
            }
            if(b==2) {
               updateRecord(); 
            }
            
            else {
               
               updateRecord();
            }
            
        }
        if(a==3) {
        //this is BACK OPTION for reverse operation
        while(true) {
        System.out.println("1.ADD RECORD\n2.VIEW RECORD\n3.SEARCH RECORD\n4.UPDATE RECORD\n5.Generate Time Table\n6.EXIT\n\nPlease Chooose an Option: ");
        int c = input.nextInt();
            
        if(c==1) {
            add_record();  
        }
        if(c==2) {
            ViewRecord();
        }
        if(c==3) {
            Searching();
        }
        if(c==4) {
            updateRecord();
        }
        if(c==5) {
           TimeTable(); 
        }
        if(c==6) {
            DeletingFileContents();
        
            System.exit(0);
        }
        
        else {
            System.out.println("Please Select from Given Options");
            updateRecord();
            
        }
        
    }
        }
        }
        catch(EOFException ex) { //catching an exxeception...
            System.out.print("No Record Found");
        }
    }
    
    
    
    //ThIS METHOD IS FOR DELETING FILE BYTES FROM ALL FILES
    public static void DeletingFileContents() throws FileNotFoundException, IOException {
        File Classess = new File("TotalClasses.java");
        //DELETING DATA FORM FILES
        RandomAccessFile put = new RandomAccessFile(Classess, "rw");
        put.setLength(0);//SETTING FILE SIZE TO ZERO
        
        File Subjects = new File("SubjectsRecord.java");
    
        RandomAccessFile pt = new RandomAccessFile(Subjects, "rw");
        pt.setLength(0);//SETTING FILE SIZE TO ZERO
        
        File Room = new File("RoomsRecord.java");
   
        RandomAccessFile ptt = new RandomAccessFile(Room, "rw");
        ptt.setLength(0);//SETTING FILE SIZE TO ZERO
           
        
    }
  //THIS MEHTOD IS FOR SEARHCING DATA ALL FILES
 public static void Searching() throws IOException, ClassNotFoundException {
     Scanner input = new Scanner(System.in);
     System.out.print("Which Record you want to search\n1.Rooms\n2.Classes\n3.Subjects\n");
     int a = input.nextInt();
     if(a==1) {
         System.out.println("ROOMS");
         RoomView();     
}
     if(a==2) {
         System.out.println("SUBJECTS");
         SubjectsView();
     }
     if(a==3) {
         System.out.println("CLASSES");
         ClassesView();
     }
 }
    
    
    //THIS MEHTOD IS FOR TIME TABLE GENERATION
  public static void TimeTable() throws FileNotFoundException, IOException, ClassNotFoundException {
          
  Scanner inpt = new Scanner(System.in);
try{ //IT IS FOR THROWING ERRORS....
//IT IS FOR Starting Date
System.out.println("Enter Exam Starting year yyyy:");//.................
String Y = inpt.next();
System.out.println("Enter Exam Starting Month mm:");//.........
String M = inpt.next();
System.out.println("Enter Exam Starting Date dd:");//..............
String D = inpt.next();

//It is for Ending Date
System.out.print("Enter Exam Excpected Ending  year yyyy:");
String eY = inpt.next();
System.out.print("Enter Exam Expected Ending  Month mm:");
String eM = inpt.next();
System.out.print("Enter Exam Excpected Ending Date dd:");
String eD = inpt.next();


     
//CREAING FORMAT.... FOR INPUTTED DATES.........   
LocalDate startDate = LocalDate.parse(Y+"-"+M+"-"+D); //START DATE
LocalDate endDate =LocalDate.parse(eY+"-"+eM+"-"+eD); //END DATE

final int days = (int) ChronoUnit.DAYS.between(startDate, endDate); //TAKING TOTAL DAYS

//ARRAY FOR STORING DATES
LocalDate[] array = new LocalDate[days];

int s=0; 
//CODITIONG IF START DATE LESS THAN ENDING DATE
while (!startDate.isAfter(endDate)) {
 
 startDate = startDate.plusDays(1); //ADDING 1 DAY TO DATE
 if (!(startDate.getDayOfWeek() == DayOfWeek.SUNDAY)) { //FOR SUNDAY
     startDate = startDate.plusDays(0); //NO DAY ADDS

}
 else {
     startDate = startDate.plusDays(1);
    
}
 array[s] = startDate; //ARRAY OF DATES
 s++;
}
String time = "9:00AM-1200PM";
 String[] myTime = new String[days];  //TIME ARRAY
 for(int k=0;k<days;k++) {
    myTime[k] = time;
 }
 
 
 //READING DATA FROM  FILE
 try (ObjectInputStream input =new ObjectInputStream(new FileInputStream("RoomsRecord.java"));
                ) {
            int room = (int)(input.readObject());
            String[] Roomss = (String[])(input.readObject());
 
 
 //READING DATA FROM  FILE   
 try (ObjectInputStream inputt =new ObjectInputStream(new FileInputStream("SubjectsRecord.java"));
                ) {   
            String[] Classes = (String[])(inputt.readObject());
                      
            
            
            
 //DISPALYING DATA         
 for (int j = 0; j < array.length; j++) {
     
     if(j==0) {
         System.out.println();
     }
     else { //DISPLAYING DATE AND TIME
     System.out.println("    "+array[j] + "   "+myTime[j]);
     }
     
  for(int u=0;u<Classes.length;u++) {
       System.out.print("    "+Classes[u]+"    "); 
 
 }
 if(Roomss.length>Classes.length) { //ROOMS>CLASSES THAN NOT SHOWING LAST ROOM
     for(int x=0;x<Roomss.length-1;x++) {
    System.out.print("    "+Roomss[x]);

     }
 }
  
 for(int x=0;x<Roomss.length;x++) { //ROOMS DISPLAYING
    System.out.print("    "+Roomss[x]);
}
 if(Roomss.length<Classes.length) { //LAST CLASS TIME IF ALL ROOMS FILLED
    myTime[j] = "12:00AM-03:00PM";
    System.out.print("             Last-Class"+myTime[j]);
 
 }
 
 }
 }
 }

}
catch(DateTimeParseException ex) { //CATCHING ERRORS
 System.out.print("Wrong Format!\nWrite in yy/mm/dd\n");
 
TimeTable();//RECALLING
 
}
catch(ArrayIndexOutOfBoundsException ex) { // CATCHING ERRORS
    System.out.println("Date Differnce should be minimum 10\n");
    TimeTable();
}
 }
}
  


  


  
        
         
            
        
        
        
        
        
        
    

