package Practice.hashTable;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name){
        this.age=age;
        this.height=height;
        this.name=name;
    }
    @Override
    public int hashCode(){
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode*31+Float.hashCode(height);
        hashCode = hashCode*31+(name!=null?name.hashCode():0);
        return hashCode;

    }//only change hashcode map result still 2

    @Override
    public boolean equals(Object obj){

        Person person = (Person) obj;
        return person.age==this.age&&person.height==this.height
                &&(person.name==null? name==null: person.name.equals(this.name));
    }

    public static void main(String[] args){
        Map<Object,Object> map=new HashMap<>();
        Person person1 =new Person(20,1.74f,"dal");
        Person person2 =new Person(20,1.74f,"dal");
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        map.put(person1,"abc");
        map.put(person2,"bcd");
        System.out.println(map.size());
    }
}
