import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Student implements Externalizable, Serializable {

    static final ObjectMapper objectMapper = new ObjectMapper();
    final XmlMapper xmlMapper = new XmlMapper();
    String name;
    int age;
    @JsonIgnore
    transient double gpa;

    public Student() {
    }

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                "}\n";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeDouble(gpa);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        gpa = in.readDouble();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public void saveToJson(String fileNameJSON) {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(fileNameJSON), this);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveToXML(String fileNameXML) {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            xmlMapper.writeValue(new File(fileNameXML), this);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
