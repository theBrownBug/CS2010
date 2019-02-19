package Practice.Queue;

// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;
import java.util.regex.PatternSyntaxException;

// write your matric number here: A0192770Y
// write your name here: Eeshan Jaiswal
// write list of collaborators here:
// StackOverflow, https://www.journaldev.com , Chia Zhe Min
// year 2019 hash code: q9zHqk6fXWaG5Ar2g7gv (do NOT delete this line)

class EmergencyRoom  {
    // if needed, declare a private data structure here that
    // is accessible to all methods in this class
    //ArrayList<Patient> patients ;
    public static int staticpatientNumber = 0 ;
    PatientHeap heap ;



    class Patient {
        String name ; int emergencyLevel ; int patientNumber ;
        Patient(String name , int emergencyLevel){
            this.name = name ; this.emergencyLevel = emergencyLevel ;
            staticpatientNumber++ ; this.patientNumber = EmergencyRoom.staticpatientNumber ;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getEmergencyLevel() {
            return emergencyLevel;
        }
        public void setEmergencyLevel(int emergencyLevel) {
            this.emergencyLevel = emergencyLevel;
        }
        public int getPatientNumber() { return this.patientNumber;}
        public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }
    }


    public class PatientHeap {
        private ArrayList<EmergencyRoom.Patient> patients ;
        private int binaryHeapSize = 0 ;
        private boolean heapSizeCheck(ArrayList<EmergencyRoom.Patient> patients){
            if (patients.size()>200000 || patients.size()<0){ return false; }
            return true ;
        }
        public PatientHeap(){
            this.patients = new ArrayList<>() ;
            this.binaryHeapSize = this.patients.size() ;
        }
        public EmergencyRoom.Patient parent(EmergencyRoom.Patient patient){
            int index = this.patients.indexOf(patient);
            if(index==-1){ return null ; }
            return this.patients.get( (int)(Math.floor(index/2))) ;
        }
        public EmergencyRoom.Patient left(EmergencyRoom.Patient patient){
            int index = this.patients.indexOf(patient);
            if(index==-1){ return null ; }
            return this.patients.get((index*2)) ;
        }
        public EmergencyRoom.Patient right(EmergencyRoom.Patient patient){
            int index = this.patients.indexOf(patient);
            if(index==-1){ return null ; }
            return this.patients.get( (index*2)+1) ;
        }

        public void maxHeapify(ArrayList<EmergencyRoom.Patient> patients, int index){
            if(index<0 || index>= patients.size()) { return; }
            EmergencyRoom.Patient currentPatient = patients.get(index);
            EmergencyRoom.Patient left = left(currentPatient) ;
            EmergencyRoom.Patient right = right(currentPatient) ;
            EmergencyRoom.Patient largest = null ;
            if(this.patients.indexOf(left)< binaryHeapSize && left.getEmergencyLevel()> currentPatient.getEmergencyLevel()){ largest = left ; }
            if(this.patients.indexOf(right)< binaryHeapSize && right.getEmergencyLevel()> currentPatient.getEmergencyLevel()){ largest = right ; }
            if(largest != currentPatient){
                int indexOfLargest = patients.indexOf(largest) ;
                EmergencyRoom.Patient temp = currentPatient ;
                currentPatient = largest ;
                patients.add(index , currentPatient); ;
                largest=temp ;
                patients.add(indexOfLargest,largest) ;
            }
            maxHeapify(patients, patients.indexOf(largest));
        }

        public void buildMaxHeap(ArrayList<EmergencyRoom.Patient> patients){
            for(int i = (int)Math.floor(patients.size()/2) ; i>=0 ; i--){
                maxHeapify(patients, i);
            }
        }

        public void heapSort(ArrayList<EmergencyRoom.Patient> patients){
            buildMaxHeap(patients);
            for(int counter = patients.size() ; counter>0 ; counter--){
                EmergencyRoom.Patient root = patients.get(0) ;
                EmergencyRoom.Patient lastElement = patients.get(patients.size()-1) ;
                this.binaryHeapSize -= 1 ;
                maxHeapify(patients,0);
            }
        }
        public int size(){ return this.binaryHeapSize ; }
        public boolean isEmpty(){ return this.binaryHeapSize== 0 ;}
        public void insert(EmergencyRoom.Patient patient){
            this.binaryHeapSize+=1 ;
            this.patients.add(binaryHeapSize-1, patient) ;
            buildMaxHeap(this.patients);
        }

        public Patient extractMax(ArrayList<Patient> patients){
            Patient maxEmergency = patients.get(0);
            buildMaxHeap(patients);
            return maxEmergency;
        }


        public class MyCustomComparator implements Comparator<Patient>{
            @Override
            public int compare(Patient p1 , Patient p2){
                // descending order
                int c =  Integer.compare(p2.getEmergencyLevel(),p1.getEmergencyLevel()) ;
                // if the emergency levels of both the patients is same, then compare the patient
                if(c==0){// ascending order
                    return Integer.compare(p1.getPatientNumber(), p2.getPatientNumber()) ;
                }
                return c ;
            }
        }
    }




    public class MyCustomComparator implements Comparator<Patient>{
        @Override
        public int compare(Patient p1 , Patient p2){
            // descending order
            int c =  Integer.compare(p2.getEmergencyLevel(),p1.getEmergencyLevel()) ;
            // if the emergency levels of both the patients is same, then compare the patient
            if(c==0){
                // ascending order
                return Integer.compare(p1.getPatientNumber(), p2.getPatientNumber()) ;
            }
            return c ;
        }
    }









    public EmergencyRoom() {
        //this.list = new PatientList() ;
        //this.patients = new ArrayList<>() ;
        this.heap = new PatientHeap() ;

    }
    void ArriveAtHospital(String patientName, int emergencyLvl){
        if(emergencyLevelCheck(emergencyLvl) && nameCheck(patientName)) {
            Patient newPatient = new Patient(patientName.toUpperCase(), emergencyLvl);
            heap.insert(newPatient);
            /*this.patients.add(newPatient);
            Collections.sort(this.patients, new MyCustomComparator()); */
        }
    }

    private boolean nameCheck(String name){
        if(name.length()>15){
            System.out.println("ERROR, name " + name + " is longer than 15 characters");
            return false;
        }
        return true ;
    }
    private boolean emergencyLevelCheck(int emergencyLvl){
        if(emergencyLvl<30 || emergencyLvl>100){
            System.out.println("Wrong emergency level") ;
            return false;
        }
        return  true ;
    }

    void Treat(String name) {
        Patient patient = heap.extractMax(heap.patients) ;
        heap.buildMaxHeap(heap.patients);
        }
        // no sorting required as the remove method takes care of all the things


    // returns the name of the patient with the maximum emergency
    String Query() {
        String ans = "The emergency suite is empty";
        if(this.heap.binaryHeapSize!=0){ return this.heap.patients.get(0).getName() ; }
        return ans;
    }







    void run() throws Exception {
        // do not alter this method

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
        while (numCMD-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
                //case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
                case 2: Treat(st.nextToken()); break;
                case 3: pr.println(Query()); break;
            }

        }

        pr.close();
    }
    public static void main(String[] args) throws Exception {
        // do not alter this method
        EmergencyRoom ps1 = new EmergencyRoom();
        for(int i = 0 ; i< ps1.heap.patients.size();i++){
            System.out.println(ps1.heap.patients.get(i).getName() +" " + ps1.heap.patients.get(i).getEmergencyLevel()) ;
        }
        ps1.run();



    }
}