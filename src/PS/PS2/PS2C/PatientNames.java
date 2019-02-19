package PS.PS2.PS2C;
// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0192770Y
// write your name here: Eeshan Jaiswal
// write list of collaborators here: Cormen, GeeksForGeeks
// year 2018 hash code: J4u8VaADhrUGvfqZXgNb (do NOT delete this line)

class PatientNames {
    // if needed, declare a private data structure here that
    // is accessible to all methods in this class

    // --------------------------------------------
    class Patient{
        String name ;
        final int MALE = 1 ;
        final int FEMALE = 2 ;
        int gender ;
        Patient left , right , parent ;
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
        public Patient getLeft() { return left; }
        public void setLeft(Patient left) { this.left = left; }
        public Patient getRight() { return right; }
        public void setRight(Patient right) { this.right = right; }
        public Patient getParent() { return parent; }
        public void setParent(Patient parent) { this.parent = parent; }
    }


    // --------------------------------------------
    /*
    static Patient previous = null ; // used for converting a BST into doubly linked list
    public Patient headPatient ;
    ArrayList<Patient> patientDll = new ArrayList<>() ;
    */
    //--------------------------------------------------

    public Patient rootPatient ;
    public static int numNodes ;
    public PatientNames(){ this.rootPatient= null ;}
    public PatientNames(Patient patient){
        if(patient==null){
            System.out.println("The param of constructor is null") ;
            System.exit(-1) ;
        }
        this.rootPatient = patient ;
    }
    public PatientNames(String name , int gender){ this.rootPatient = new Patient(name , gender) ; }

    // for Query function only
    private boolean verifyGender(int gender){
        return (gender>= 0 && gender<=2 ) ;
    }
    private boolean startEnd(String Start , String End){
        Start = Start.trim().toUpperCase() ;
        End = End.trim().toUpperCase() ;
        return (Start.compareTo(End)<0) ;
    }

    /**
     * get and set methods for PatientNames BinaryTree
     *
     * */
    public Patient getRootPatient() { return rootPatient; }
    public void setRootPatient(Patient rootPatient) { this.rootPatient = rootPatient; }

    void AddPatient(String patientName, int gender) {
        Patient newPatient = new Patient(patientName , gender) ;
        // checking whether the patient with same name is present ; // if not present then only add
        Patient found = searchPatient(getRootPatient() , patientName, gender) ;
        // the patient we are trying to add is new
        if (found== null) {
            Patient trailingPointer = null;
            Patient rootPatient = getRootPatient();
            while (rootPatient != null) {
                trailingPointer = rootPatient;
                if (patientName.toUpperCase().trim().compareTo(rootPatient.getName()) > 0) {
                    rootPatient = rootPatient.getRight();
                } else if (patientName.toUpperCase().trim().compareTo(rootPatient.getName()) < 0) {
                    rootPatient = rootPatient.getLeft();
                }
            }
            newPatient.setParent(trailingPointer);
            if (trailingPointer == null) {
                this.rootPatient = newPatient ;
            }
            else if (newPatient.getName().compareTo(trailingPointer.getName()) > 0) {
                trailingPointer.setRight(newPatient);
            } else if (newPatient.getName().compareTo(trailingPointer.getName()) < 0) {
                trailingPointer.setLeft(newPatient);
            }
        }
        else{
            System.out.println("Patient of the same name is already added");
            return;
        }
    }

    public Patient searchPatient(Patient rootPatient , String name ,int gender ){
        if((rootPatient==null) ||(rootPatient.getGender() == gender && rootPatient.getName().equals(name.toUpperCase().trim()))){
            return rootPatient ;
        }
        /*in the dict, the Name comes after rootPatient.getName() as compareTo() returns >  0 */
        else if(name.trim().toUpperCase().compareTo(rootPatient.getName())>0){
           return  searchPatient(rootPatient.getRight() , name , gender) ;
        }
        else if(name.trim().toUpperCase().compareTo(rootPatient.getName())<0){
           return searchPatient(rootPatient.getLeft() ,name , gender) ;
        }
        return null ;
    }


    public Patient findSuccessor(Patient patient){
        Patient parent = null ;
        if(patient.getRight()!=null){ findMinimum(patient) ; }
        else{
            Patient current = patient ;
            parent = patient.getParent() ;
            while(parent!=null && current == parent.getRight()){
                current = parent;
                parent  = parent.getParent() ;
            }
        }
        return  parent ;
    }
    public Patient findMinimum(Patient node){
        Patient minimum = node ;
        while(minimum.getLeft()!=null){minimum = minimum.getLeft() ;}
        return  minimum ;
    }

    public Patient findMaximum(Patient node){
        Patient max  = node ;
        while(max.getRight()!= null){ max = max.getRight() ; }
        return  max;
    }

    public void transplant( PatientNames tree , Patient toBeReplaced , Patient replacement){
        if(toBeReplaced.getParent()==null){ tree.setRootPatient(replacement);}
        else if(toBeReplaced == toBeReplaced.getParent().getLeft()){ toBeReplaced.getParent().setLeft(replacement); }
        else { toBeReplaced.getParent().setRight(replacement); }
        if(replacement!=null){replacement.setParent(toBeReplaced.getParent());}
    }
    void RemovePatient(String patientName) {
        Patient malePatientToBeDeleted  = searchPatient(getRootPatient(), patientName , 1) ;
        Patient femalePatientToBeDeleted= searchPatient(getRootPatient(), patientName , 2) ;
        // the patient has to exist inorder to be removed
        if(malePatientToBeDeleted!=null || femalePatientToBeDeleted!=null){
            // the patient is male
            if(malePatientToBeDeleted!=null){
                if(malePatientToBeDeleted.getLeft()== null){ transplant(this , malePatientToBeDeleted , malePatientToBeDeleted.getRight());}
                else if(malePatientToBeDeleted.getRight()==null){ transplant(this , malePatientToBeDeleted , malePatientToBeDeleted.getLeft());}
                else{
                    Patient successor = findSuccessor(malePatientToBeDeleted) ;
                    if(successor.getParent() != malePatientToBeDeleted){
                        transplant(this , successor , successor.getRight());
                        successor.setRight(malePatientToBeDeleted.getRight());
                        successor.getRight().setParent(successor);
                    }
                    transplant(this, malePatientToBeDeleted , successor);
                    successor.setLeft(malePatientToBeDeleted.getLeft());
                    successor.getLeft().setParent(successor);
                }
            }
            // the patient is female
            else{
                if(femalePatientToBeDeleted.getLeft()== null){ transplant(this , femalePatientToBeDeleted , femalePatientToBeDeleted.getRight());}
                else if(femalePatientToBeDeleted.getRight()==null){ transplant(this , femalePatientToBeDeleted , femalePatientToBeDeleted.getLeft());}
                else{
                    Patient successor = findSuccessor(femalePatientToBeDeleted) ;
                    if(successor.getParent() != femalePatientToBeDeleted){
                        transplant(this , successor , successor.getRight());
                        successor.setRight(femalePatientToBeDeleted.getRight());
                        successor.getRight().setParent(successor);
                    }
                    transplant(this, femalePatientToBeDeleted , successor);
                    successor.setLeft(femalePatientToBeDeleted.getLeft());
                    successor.getLeft().setParent(successor);
                }
            }
        }
    }

    void inorderTraversal(){
        if(getRootPatient()==null){
            return ;
        }

        Stack<Patient> list = new Stack<>() ;
        Patient current = getRootPatient() ;
        while(current!= null || list.size()>0){
            while (current!=null){
                list.add(current) ;
                current = current.getLeft() ;
                numNodes++ ;
            }
            current = list.pop() ;
            System.out.println("Patient name : "+ current.getName() + " ");
            current = current.getRight() ;
            //numNodes++ ;
        }

    }

    int Query(String START, String END, int gender) {
        if(!verifyGender(gender)){
            System.out.println("Wrong gender entered in Query Function");
            System.exit(-1);
        }
        if(!startEnd(START,END)){
            System.out.println("Start should be less than end");
            System.exit(-1);
        }
        int ans = 0;
        if(getRootPatient()==null){
            return ans ;
        }

        Stack<Patient> list = new Stack<>() ;
        Patient current = getRootPatient() ;
        while(current!= null || list.size()>0){
            while (current!=null){
                list.add(current) ;
                current = current.getLeft() ;
            }
            current = list.pop() ;
            /*
            * using only first letter of the name as condition in Part A states
            * */
            if((current.getName().substring(0,1).compareTo(START.toUpperCase().trim()) >0)
                    && (current.getName().substring(0,1).compareTo(END.trim().toUpperCase())<0)){
                if (gender == 0) { ans++ ;}
                else if(gender==current.getGender()) { ans += 1; }
            }
            current = current.getRight() ;
        }

        return ans;
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