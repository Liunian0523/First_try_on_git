package Comp353.A1Q2;

public class School {
    public static void main(String[] args) {

        Student s1 = new Student("Ada",112233,false,0);
        Student s2 = new Student("Baby",223344,true,69);
        Student s3 = new Student("Cindy",334455,true,75);
        Student s4 = new Student("David",445566,true,89);
        Student s5 = new Student("Eva",556677,true,99);
        Student s6 = new Student("Fendi",667788,false,0);
        Student s7 = new Student("Gaga",778899,true,50);
        Student s8 = new Student("Hanna",889911,false,0);
        Student s9 = new Student("Ivan",991122,true,81);

        Student[] database353 = new Student[]{s1,s2,s3,s4,s5,s6,s7,s8,s9};
        System.out.println("Find the name and ID of every student who took Databases" +
                " and obtained B+ or a better grade:");
        for(int i=0; i < database353.length; i++){
            if(database353[i].takeClass=true){
                if(database353[i].grade>=75){
                    System.out.println("StudentName: "+database353[i].name+", StudentID: "+database353[i].sid+
                            ", StudentGrade: "+database353[i].calculateGrade(database353[i].grade)+".");
                }
            }
        }
    }
}
class Student {
    String name;
    int sid;
    boolean takeClass = true;
    int grade;

    public Student(){

    }

    public Student(String n, int id, boolean database, int result){
        name = n;
        sid = id;
        takeClass = database;
        grade = result;

    }

    public String calculateGrade(int n){
        String g = "";
        if(n>=90){
            g = "A+";
        }
        else if(n>=85){
            g = "A";
        }
        else if(n>=80){
            g = "A-";
        }
        else if(n>=75){
            g = "B+";
        }
        return g;
    }

}
