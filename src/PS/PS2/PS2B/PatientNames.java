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
        int hashCode ;
        int gender ;
        int rank ;
        public Patient(String name , int gender){
            if(name.toUpperCase().trim().length()<30) {
                this.name = name.toUpperCase().trim();
                this.hashCode = getName().hashCode() ;
            }
            else{ System.out.println(name+" is longer than 30 characters"); System.exit(-1);}
            if(gender==1 || gender== 2){ this.gender = gender ; }
            else{ System.out.println(" Wrong gender code") ; System.exit(-1);
            }

        }


        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getGender() { return gender; }
        public void setGender(int gender) { this.gender = gender; }



        @Override
        public int compareTo(Patient o) {
            return  this.getName().compareTo(o.getName()) ;
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
            return  false ;
       }

    }


    TreeSet<Patient> tree = new TreeSet<>() ;
    TreeMap<String, Patient> map = new TreeMap<>() ;
    //TreeMap<Integer,Patient> patientMap = new TreeMap<>() ;
    //TreeMap<String, Integer> ageMapMale = new TreeMap<>() ;
    //TreeMap<String, Integer> ageMapFemale = new TreeMap<>() ;



    public void AddPatient(String name,  int gender){
        if(!contains(name)){
            tree.add(new Patient(name, gender));
            name = name.toUpperCase().trim();
            map.put(name, new Patient(name,gender)) ;


            //if(gender==1){ageMapMale.put(name,gender) ; }
            //else{ageMapFemale.put(name,gender) ; }
            //patientMap.put(name.hashCode(),new Patient(name,gender)) ;
        }else{
            System.out.println("Patient with the same name is already present");
            System.exit(-1);
        }
    }


    private boolean contains(String name){
        name = name.toUpperCase().trim() ;
        if(map.containsKey(name)) {
            //if (patientMap.containsKey(map.get(name))) {
                //if(patientMap.get(name.hashCode())!=null)
                    return true;
            //}
        }
        return false;
        /*
        Iterator iterator = tree.iterator() ;
        while(iterator.hasNext()){
            Patient patient = (Patient) iterator.next() ;
            if(patient.getName().equals(name.trim().toUpperCase())){
                return  true ;
            }
        }
        return  false;
        */

    }

    private Patient searchPatient(String name){
        Patient present = null;
        if(contains(name.trim().toUpperCase())){
            //present = patientMap.get(name.trim().toUpperCase().hashCode()) ;
            present = map.get(name) ;
            /*
            Iterator iterator = tree.iterator() ;
            while(iterator.hasNext()){
                Patient current = (Patient) iterator.next() ;
                if(current.getName().equals(name.toUpperCase().trim())){
                    present = current ;
                }
            }
           */
        }
        return present ;
    }

    private boolean genderCheck(int gender){ return (gender>= 0 && gender<=2) ;}

    public void RemovePatient(String name){
        if(contains(name)){
            Patient searched = searchPatient(name.toUpperCase().trim()) ;
            tree.remove(searched) ;
            map.remove(name) ;
            //patientMap.remove(name.toUpperCase().trim().hashCode()) ;
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
        START = START.toUpperCase().trim();
        END = END.toUpperCase().trim() ;
        NavigableMap<String,Patient> subMap = map.subMap(START,true,END,false) ;
        //NavigableMap<String,Integer> subMapMale = ageMapMale.subMap(START,true,END,false) ;
        //NavigableMap<String,Integer> subMapFemale = ageMapFemale.subMap(START,true,END,false) ;

        /*
        if((START.hashCode()- END.hashCode())<=0){
            subMap  = patientMap.subMap(START.hashCode(), END.hashCode()) ;
        }
        else{
            int min = (START.hashCode()> END.hashCode())? END.hashCode(): START.hashCode() ;
            int max = (START.hashCode()>END.hashCode())? START.hashCode(): END.hashCode() ;
            subMap= patientMap.subMap(min , max) ;
        }
        */
        if(gender==0){
            return subMap.size() ;
        }
        //else if(gender==1){ return subMapMale.size() ; }
        else{
         //   return  subMapFemale.size() ;
            /*
            Object[] list = subMap.values().toArray() ;
            for(Object p :list){
                if(((Patient)p).getGender()==gender){
                    ans+=  1 ;
                }
            }*/

        }

        /*
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
        */
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