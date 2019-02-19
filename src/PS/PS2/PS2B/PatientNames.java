package PS.PS2.PS2B ;

// Copy paste this Java Template and save it as "PatientNames.java"
import java.nio.charset.Charset;
import java.util.*;
import java.io.*;

// write your matric number here: A0192770Y
// write your name here: Eeshan Jaiswal
// write list of collaborators here: Cormen, GeeksForGeeks
// year 2018 hash code: J4u8VaADhrUGvfqZXgNb (do NOT delete this line)

// PS2 B

class PatientNames {
    class Patient implements Comparable<Patient>{
        String name ;
        int gender ;
        public Patient(String name , int gender){
            if(name.toUpperCase().trim().length()<30) { this.name = name.toUpperCase().trim(); }
            else{ System.out.println(name+" is longer than 30 characters"); System.exit(-1);}
            if(gender==1 || gender== 2){ this.gender = gender ; }
            else{ System.out.println(" Wrong gender code") ; System.exit(-1);}
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getGender() { return gender; }
        public void setGender(int gender) { this.gender = gender; }

        @Override
        public int compareTo(Patient o) {
            return  (this.getName().compareTo(o.getName())) ;
        }


        public boolean validPosition(String START, String END){
            START = START.trim().toUpperCase() ;
            END = END.trim().toUpperCase() ;
            char[] startCharArray = START.toCharArray() ;
            char[] endCharArray   = END.toCharArray() ;
            char[] nameCharArray  = this.getName().toCharArray() ;

            //boolean condition = true ;

            // subtract 1 if hash code not used
            int minStartComparison = Math.min(startCharArray.length, nameCharArray.length) ;
            int minEndComparison = Math.min(endCharArray.length , nameCharArray.length) ;

            if((START.substring(0,minStartComparison).hashCode()<= this.getName().substring(0,minStartComparison).hashCode())){

                if(END.substring(0,minEndComparison).equals(this.getName().substring(0,minEndComparison))){
                        if(END.hashCode()> this.getName().hashCode()){
                            return  true ;
                        }
                }
                else{
                    if(END.substring(0,minEndComparison).hashCode()> this.getName().substring(0,minEndComparison).hashCode())
                        return true;
                }
            }
            /*
            int counter = 0;
            while(counter <= minStartComparison){
                if(startCharArray[counter] <= nameCharArray[counter]){
                    counter ++;
                    continue ;
                }
                else{ condition = false;  return  condition ; }

            }

            counter = 0 ;
            if(condition){
                while (counter<= minEndComparison){
                    if(endCharArray[counter]>= nameCharArray[counter]){
                        counter++ ;
                        continue;
                    }
                    else{condition = false ;  return  condition ; }
                }
            }
            return  condition ;
            */
            return  false ;
       }

    }
    TreeSet<Patient> tree = new TreeSet<>() ;

    public void AddPatient(String name,  int gender){
        if(!contains(name.toUpperCase().trim())) {
            tree.add(new Patient(name, gender));
        }else{
            System.out.println("Patient with the same name is already present");
            System.exit(-1);
        }
    }

    private boolean contains(String name){
        Iterator iterator = tree.iterator() ;
        while(iterator.hasNext()){
            Patient patient = (Patient) iterator.next() ;
            if(patient.getName().equals(name.trim().toUpperCase())){
                return  true ;
            }
        }
        return  false;
    }

    private Patient searchPatient(String name){
        Patient present = null;
        if(contains(name.trim().toUpperCase())){
            Iterator iterator = tree.iterator() ;
            while(iterator.hasNext()){
                Patient current = (Patient) iterator.next() ;
                if(current.getName().equals(name.toUpperCase().trim())){
                    present = current ;
                }
            }
        }
        return present ;
    }

    private boolean genderCheck(int gender){ return (gender>= 0 && gender<=2) ;}

    public void RemovePatient(String name){
        if(contains(name)){
            Patient searched = searchPatient(name.toUpperCase().trim()) ;
            tree.remove(searched) ;
        }
        else{
            System.out.println("The patient is not present");
        }
    }

    public int Query(String START , String END , int gender){
        int ans  = 0 ;
        if(!genderCheck(gender)){
            System.out.println("Wrong gender");
            return  ans;
        }

        Iterator iterator = tree.iterator() ;
        while(iterator.hasNext()){
            Patient currentPatient = (Patient) iterator.next() ;
            if(gender==0){
                if(currentPatient.validPosition(START,END)){
                    ans++ ;
                }
            }else{
                if( currentPatient.validPosition(START,END) && currentPatient.getGender()==gender){
                    ans++ ;
                }
            }

        }
        return  ans ;
    }


    void run() throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 0) // end of input
                break;
            else if (command == 1) // AddPatient
                AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
            else if (command == 2) // RemovePatient
                RemovePatient(st.nextToken());
            else // if (command == 3) // Query
                pr.println(Query(st.nextToken(), // START
                        st.nextToken(), // END
                        Integer.parseInt(st.nextToken()))); // GENDER
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        PatientNames ps2 = new PatientNames();
        //System.out.println("pet".compareTo("apple"));
        ps2.run();
        /*
        ps2.AddPatient("JANE" , 2);
        ps2.AddPatient("EESHAN",1);
        ps2.AddPatient("JOSHUA", 1);
        ps2.AddPatient("MARIA" , 2);
        ps2.AddPatient("PETER" , 1);
        ps2.inorderTraversal();
        System.out.println(numNodes);
        */
    }




}