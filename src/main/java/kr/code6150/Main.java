package kr.code6150;

import com.google.gson.Gson;
import kr.code6150.obj.Student;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        /*Student st = new Student("홍길동", 1);
        st.setName("a");*/

        Student st = new Student("홍길동", 15);
        //json
        //{fieldname: Value}
        //google에서 만든 object -> json 만들어주는 library [Gson]
        // "홍길동" + ".json" -> "홍길동.json"
        File file = new File(st.getName() + ".json");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.out.println("파일생성에 실패했습니다.");
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(st);
        try (FileWriter fw = new FileWriter(file)) {
            // Input Output Exception
            fw.write(json);
        } catch (IOException ex) {
            System.out.println("파일에 쓰는데 실패 했습니다.");
        }

        //---------------------------File -> Student
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            // int
            String data = br.readLine();
            Student fromeJsonStudent = gson.fromJson(data, Student.class);
            System.out.println(fromeJsonStudent.getName());
            System.out.println(fromeJsonStudent.getAge());
        } catch (FileNotFoundException fnfe) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException ioe) {
            System.out.println("파일을 읽는데 실패 했습니다.");
        }
    }
}