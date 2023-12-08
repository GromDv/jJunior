import java.io.*;
import java.util.ArrayList;

public class MainApp {


    public static void main(String[] args) {

        Student student1 = new Student("Ivanov", 18, 4.5);

        System.out.println("Student before:");
        System.out.println(student1);

        student1.saveToJson(student1.getName() + ".json");
        student1.saveToXML(student1.getName() + ".xml");


        String fileName = student1.getName() + ".bin";
        try (ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objOutStream.writeObject(student1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Student student1_s;
        fileName = student1.getName() + ".bin";

        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(fileName))) {
            student1_s = (Student) objInStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Student deserialized:");
        System.out.println(student1_s);

        ArrayList<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(new Student("Petrov", 19, 4.8));
        list.add(new Student("Ivanov", 20, 3.5));

        System.out.println("List of students before:");
        System.out.println(StudentsList.show(list));

        StudentsList.saveListToFile("list.json", list);
        StudentsList.saveListToFile("list.xml", list);
        StudentsList.saveListToFile("list.bin", list);

        ArrayList<Student> listJson = StudentsList.loadListFromFile("list.json");
        System.out.println("List of students from *.json:");
        System.out.println(StudentsList.show(listJson));

        ArrayList<Student> listXml = StudentsList.loadListFromFile("list.xml");
        System.out.println("List of students from *.xml:");
        System.out.println(StudentsList.show(listXml));

        ArrayList<Student> listBin = StudentsList.loadListFromFile("list.bin");
        System.out.println("List of students from *.bin:");
        System.out.println(StudentsList.show(listBin));
    }
}
