import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsList {

    static final ObjectMapper objectMapper = new ObjectMapper();
    static final XmlMapper xmlMapper = new XmlMapper();

    public static String show(List<Student> list) {
        StringBuilder result = new StringBuilder();
        for (Student st : list) {
            result.append(st.toString());
        }
        return result.toString();
    }
    public static void saveListToFile(String fileName, List<Student> list) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), list);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(list);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> loadListFromFile(String fileName) {
        ArrayList<Student> list = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    list = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Student.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        list = (ArrayList<Student>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    list = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, Student.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
