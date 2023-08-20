package school;

import java.lang.reflect.Field;

public class Main {
    /*
    Make it by analogy with the example with the @MyName annotation (see the code in the repository):

    There is a Student class with name, surname, age fields. Create @StudentInfo annotation and corresponding injector through reflection,
    in order to be able to initialize variables of type Student with their help. For example:
    public class School {
        @StudentInfo(name = "Tom", surname = "Smith", age = 15)
        private Student student1;

        @StudentInfo(name = "Mary", surname = "Jennings", age = 17)
        private Student student2;
    }
    */

    public static void main(String[] args) {
        School school = new School();

        try {
            inject(school);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(school);
    }

    private static void inject(School school) throws IllegalAccessException {
        for (Field declaredField : school.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(StudentInfo.class)) {
                StudentInfo studentInfo = declaredField.getAnnotation(StudentInfo.class);

                declaredField.setAccessible(true);
                declaredField.set(school, new Student(studentInfo.name(), studentInfo.surname(), studentInfo.age()));
            }
        }
    }



}
