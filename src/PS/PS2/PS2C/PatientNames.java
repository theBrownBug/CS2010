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
        int height;

        Patient left , right , parent ;
        public Patient(String name , int gender){
            if(name.toUpperCase().trim().length()<30) { this.name = name.toUpperCase().trim(); }
            else{ System.out.println(name+" is longer than 30 characters"); System.exit(-1);}
            if(gender==1 || gender== 2){ this.gender = gender ; }
            else{ System.out.println(" Wrong gender code") ; System.exit(-1);}
            this.height = 0 ;
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
        public int getHeight() { return height; }
        public void setHeight(int height) { this.height = height; }
    }


    public Patient rootPatient ;
    public static int numNodes = 0 ;
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

    HashMap<String,Patient> patientHashMap = new HashMap<String,Patient>() ;


    public Patient searchPatient(Patient rootPatient , String name ,int gender ){
        name= name.toUpperCase().trim() ;
        boolean validGender = (gender==1 || gender==2) ? true : false ;
        if(!validGender){System.out.println("Wrong gender in search Patient method") ; return  null; }

        if((rootPatient==null) ||(rootPatient.getGender() == gender && rootPatient.getName().equals(name))){
            return rootPatient ;
        }
        /*in the dict, the Name comes after rootPatient.getName() as compareTo() returns >  0 */
        else if(name.compareTo(rootPatient.getName())>0){
            return  searchPatient(rootPatient.getRight() , name , gender) ;
        }
        else if(name.compareTo(rootPatient.getName())<0){
            return searchPatient(rootPatient.getLeft() ,name , gender) ;
        }
        return null ;
    }


    void AddPatient(String patientName, int gender) {
        Patient newPatient = new Patient(patientName , gender) ;
        patientName = patientName.trim().toUpperCase() ;
        // checking whether the patient with same name is present ; // if not present then only add
        Patient found = searchPatient(getRootPatient() , patientName, gender) ;
        // the patient we are trying to add is new
        if (found== null) {
            patientHashMap.put(patientName, newPatient) ;

            Patient trailingPointer = null;
            Patient rootPatient = getRootPatient();
            while (rootPatient != null) {
                trailingPointer = rootPatient;
                if (patientName.compareTo(rootPatient.getName()) > 0) {
                    rootPatient = rootPatient.getRight();
                } else if (patientName.compareTo(rootPatient.getName()) < 0) {
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



            //newPatient.setHeight(1+ max(getHeight(newPatient.getLeft()) , getHeight(newPatient.getRight())));
            newPatient.setHeight(1);
            int balanceFactor ;

            Patient parent = newPatient.getParent() ;
            Patient current= newPatient ;

            // backtrack up the tree
            while(parent!= null){
                /*
                balanceFactor = getBalanceFactor(current) ;

                // left left case
                if((balanceFactor> 1) && (newPatient.getName().compareTo(newPatient.getLeft().getName())<0)){
                    rightRotate(newPatient) ;
                }
                // right right case
                if((balanceFactor<-1) && (newPatient.getName().compareTo(newPatient.getRight().getName())> 0 )){
                    leftRotate(newPatient);
                }

                //left right
                if ((balanceFactor>1) && (newPatient.getName().compareTo(newPatient.getLeft().getName())>0)){
                    rightRotate(newPatient);
                }

                // right left
                if((balanceFactor<-1) && (newPatient.getName().compareTo(newPatient.getRight().getName())>0)){
                    leftRotate(newPatient);
                }
                */
                parent.setHeight(1+ max(getHeight(parent.getLeft()) , getHeight(parent.getRight())));
                balance(current);
                current = parent ;
                parent = parent.getParent() ;


            }



            }

            //balance(newPatient);

    }




    /**
     *
     *  AVL TREE FUNCTIONS
     *
     * */

    /*====================================================================================================*/

    public int getHeight(Patient patient){
        if(patient==null){return 0; }
        // this getHeight of the Patient Class
        return patient.getHeight();
    }

    public int max(int a , int b){
        return (a>b)? a:b ;
    }

    public Patient rightRotate(Patient patient){
        Patient left = patient.getLeft()  ;
        Patient rightSubOfLeft = left.getRight() ;

        left.setRight(patient);
        patient.setLeft(rightSubOfLeft);
        //update heights
        patient.setHeight(max(getHeight(patient.getLeft()) , getHeight(patient.getRight()) ) + 1 ) ;
        left.setHeight(max(getHeight(left.getLeft()) , getHeight(left.getRight()) ) + 1);

        return left ;
    }
    public Patient leftRotate(Patient patient){
        Patient right = patient.getRight() ;
        Patient leftSub = right.getLeft() ;

        right.setLeft(patient);
        patient.setRight(leftSub) ;

        patient.setHeight(max(getHeight(patient.getLeft()) , getHeight(patient.getRight())) + 1);
        right.setHeight(max(getHeight(right.getLeft()), getHeight(right.getLeft()))+1);

        return  right ;
    }

    public int getBalanceFactor(Patient p){
        if(p==null)
            return 0;
        return getHeight(p.getLeft())- getHeight(p.getRight()) ;
    }






    public void balance(Patient patient) {
        Patient current = patient;
        Patient parent = current.getParent();
        while (parent != null) {
            int balanceFactor = getBalanceFactor(current);
            // the right subtree of the current node has more height
            if (balanceFactor < -1) {
                // right left case
                if (getBalanceFactor(current.getRight()) > 0) {
                    patient.setRight(rightRotate(patient.getRight()));
                }
                // left rotation is a must in both RR and RL cases
                leftRotate(current.getLeft());
            }

            // the left subtree of the current node is much higher
            else if (balanceFactor > 1) {
                // the right subtree has more height LR case
                if (getBalanceFactor(current.getLeft()) < 0) {
                    patient.setLeft(leftRotate(current.getLeft()));
                }
                rightRotate(patient);
            }

            // in the last iteration this will break out of the loop
            if(current.getParent()==null){break;}
            current = parent ;
            if(parent.getParent()==null){continue; }

            parent = parent.getParent();
            /*
            if(current.getParent()!=null) {
                parent = parent.getParent();
                continue;
            }
            */
            //if(current==null){break; }

        }
    }


    /*====================================================================================================*/



    public Patient findSuccessor(Patient patient){
        Patient parent = null ;
        if(patient.getRight()!=null){  return findMinimum(patient.getRight()) ; }
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
        //Patient malePatientToBeDeleted  = searchPatient(getRootPatient(), patientName , 1) ;
        //Patient femalePatientToBeDeleted= searchPatient(getRootPatient(), patientName , 2) ;
        Patient toBeRemoved = patientHashMap.get(patientName) ;
        Patient toBeRemovedFromTree = searchPatient(getRootPatient(),toBeRemoved.getName(),toBeRemoved.getGender()) ;
        // the patient has to exist inorder to be removed
            // the patient is male
            if(toBeRemovedFromTree!=null){
                if(toBeRemovedFromTree.getLeft()== null){ transplant(this , toBeRemovedFromTree , toBeRemovedFromTree.getRight());}
                else if(toBeRemovedFromTree.getRight()==null){ transplant(this , toBeRemovedFromTree , toBeRemovedFromTree.getLeft());}
                else{
                    Patient successor = findSuccessor(toBeRemovedFromTree) ;
                    if(successor.getParent() != toBeRemovedFromTree){
                        transplant(this , successor , successor.getRight());
                        successor.setRight(toBeRemovedFromTree.getRight());
                        successor.getRight().setParent(successor);
                    }
                    transplant(this, toBeRemovedFromTree , successor);
                    successor.setLeft(toBeRemovedFromTree.getLeft());
                    successor.getLeft().setParent(successor);
                }

                patientHashMap.remove(toBeRemoved.getName()) ;

                if (getRootPatient()==null)
                    return ;

               // malePatientToBeDeleted.setHeight(max(getHeight(malePatientToBeDeleted.getRight()),getHeight(malePatientToBeDeleted.getLeft())) + 1);


                // check balance
                //int balance = getBalanceFactor(malePatientToBeDeleted) ;

                // if(balance

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
            System.out.println("Patient name : "+ current.getName() + "  Height : " + current.getHeight()+" Balance Factor "+ getBalanceFactor(current));
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
        //ps2.run();

        ps2.AddPatient("JANE" , 2);
        ps2.AddPatient("EESHAN",1);
        ps2.AddPatient("ABBY",2);
       /*
        ps2.AddPatient("ALASTAIR", 1);
        ps2.AddPatient("JOSHUA", 1);
        ps2.AddPatient("MARIA" , 2);
        ps2.AddPatient("PETER" , 1);
        ps2.AddPatient("JODI",2);
        */
        ps2.inorderTraversal();

        System.out.println();
        //ps2.RemovePatient("EESHAN");
        //ps2.inorderTraversal();
        //System.out.println(numNodes);

    }
}