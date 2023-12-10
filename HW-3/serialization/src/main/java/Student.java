import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Student implements Externalizable {

    //region Fields
    static ObjectMapper objectMapper = new ObjectMapper();
    static final XmlMapper xmlMapper = new XmlMapper();
    private String name;
    private int age;
    @JsonIgnore
    transient private double gpa;

    //endregion

    //region Constructors
    public Student() {
    }

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    //endregion

    //region Getters-Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    //endregion

    //region Methods
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }

    public void saveToJson() {
        String fileNameJSON = this.name + ".json";
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(fileNameJSON), this);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveToXML() {
        String fileNameXML = this.name + ".xml";
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            xmlMapper.writeValue(new File(fileNameXML), this);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Student loadStudent(String fileName) {
        File file = new File(fileName);
        Student result = new Student();
        try {
            if (fileName.endsWith(".json")) {
                result = objectMapper.readValue(file, Student.class);
            } else if (fileName.endsWith(".xml")) {
                result = xmlMapper.readValue(file, Student.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                "}\n";
    }
    //endregion
}
