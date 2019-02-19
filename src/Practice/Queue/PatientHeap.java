package Practice.Queue;
/*
import java.util.ArrayList;
import java.util.Arrays;

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
        return this.patients.get( (int)(Math.floor(index*2))) ;
    }
    public EmergencyRoom.Patient right(EmergencyRoom.Patient patient){
        int index = this.patients.indexOf(patient);
        if(index==-1){ return null ; }
        return this.patients.get( (int)(Math.floor(index*2)+1)) ;
    }

    public void maxHeapify(ArrayList<EmergencyRoom.Patient> patients, int index){
        if(index<0 || index> patients.size()) { return; }
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
        for(int i = (int)(Math.floor(patients.size())) ; i>=1 ; i--){
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
        this.binaryHeapSize++ ;
        this.patients.add(binaryHeapSize, patient) ;
        buildMaxHeap(this.patients);
    }


}

*/