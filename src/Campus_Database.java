import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int year;
    private double gpa;

    Student(String name, int year, double gpa) {
        this.name = name;
        this.year = year;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getGPA() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-4d | %-4.2f", name, year, gpa);
    }
}

public class Campus_Database {
    static Scanner sc = new Scanner(System.in);

    static double totalGPA = 0;

    static void clearUI() {
        for(int i = 0; i < 30; i++) System.out.printf("\n");
    }

    static void printMenu() {
        System.out.println("1. Add student data");
        System.out.println("2. Remove student data");
        System.out.println("3. Search student data");
        System.out.println("4. Average student GPA");
        System.out.println("5. Exit");
    }

    static void printStudentList(ArrayList<Student> studentList) {
        if(studentList.isEmpty()) return;

        System.out.println("No | Name       | Year | GPA  |");
        System.out.println("================================");
        for(int i = 0; i < studentList.size(); i++) System.out.printf("%-2d | %s |\n", i + 1, studentList.get(i));
        System.out.println("================================");
    }

    static void addStudent(ArrayList<Student> studentList) {
        String name = "";
        int year = 0;
        double gpa = 5;

        while(name.length() < 3 || name.length() > 12) {
            System.out.printf("Enter new student name: ");
            name = sc.nextLine();
        }

        while(year < 2000 || year > 2022) {
            System.out.printf("Enter new student year: ");
            year = sc.nextInt(); sc.nextLine();
        }

        while(gpa < 0 || gpa > 4) {
            System.out.printf("Enter new student gpa: ");
            gpa = sc.nextDouble(); sc.nextLine();
        }

        studentList.add(new Student(name, year, gpa));
        totalGPA = totalGPA + gpa;

        System.out.printf("Press enter to continue..."); sc.nextLine();
    }
    
    static void exit() {
        System.out.println("Bye~");
    }
    
    static void removeStudent(ArrayList<Student> studentList) {
        if(studentList.isEmpty()) {
            System.out.println("There is no student data to delete");
            System.out.printf("Press enter to continue..."); sc.nextLine();
            return;
        }
        
        int no = 0;
        while(no <= 0 || no > studentList.size()) {
            System.out.printf("Enter data No. to be deleted: ");
            no = sc.nextInt();
        }
        
        totalGPA = totalGPA - studentList.get(no - 1).getGPA();
        studentList.remove(no - 1);
        
        System.out.printf("Press enter to continue..."); sc.nextLine();
    }
    
    static void searchStudent(ArrayList<Student> studentList) {
        String find;
        System.out.printf("Enter student name to search: ");
        find = sc.nextLine();
        
        for(int i = 0; i < studentList.size(); i++) {
            Student currList = studentList.get(i);
            if(currList.getName().equals(find)) {
                System.out.println("Student data is available:");
                System.out.printf("Name: %s\n", currList.getName());
                System.out.printf("Year: %d\n", currList.getYear());
                System.out.printf("GPA: %.2f\n", currList.getGPA());
                
                System.out.printf("Press enter to continue..."); sc.nextLine();
                return;
            }
        }
        
        System.out.println("There is no student data");

        System.out.printf("Press enter to continue..."); sc.nextLine();
    }
    
    static void averageGPA(ArrayList<Student> studentList) {
        System.out.printf("Average GPA of %d students: %.2f\n", studentList.size(), totalGPA / studentList.size());
        System.out.printf("Press enter to continue..."); sc.nextLine();
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Student> studentList = new ArrayList<>();

        int menu = 0;
        do {
            clearUI();
            printStudentList(studentList);
            printMenu();

            System.out.printf("Choose: ");
            menu = sc.nextInt(); sc.nextLine();

            if(menu == 1) addStudent(studentList);
            else if(menu == 2) removeStudent(studentList);
            else if(menu == 3) searchStudent(studentList);
            else if(menu == 4) averageGPA(studentList);
            else exit();

        } while(menu != 5);

        sc.close();
    }
}
