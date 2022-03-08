import java.util.List;
import java.util.Scanner;

public class ClassDirector {
	private final CsvLoader courseCsvLoader = new CsvLoader("course.csv", "utf-8");
	private List<Course> courses = courseCsvLoader.loadCsv(line -> {
        Course course = new Course();
        course.setName(line[0]);
        course.setRequirements(line[1]);
        course.setTime(line[2]);
        course.setLocation(line[3]);
        return course;
    });;
	private Scanner scanner = new Scanner(System.in);
	
	public void input() {
		Course course = new Course();
        System.out.print("input course name: ");
        course.setName(scanner.nextLine());
        System.out.print("input course requirements: ");
        course.setRequirements(scanner.nextLine());
        System.out.print("input course time: ");
        course.setTime(scanner.nextLine());
        System.out.print("input course location: ");
        course.setLocation(scanner.nextLine());
        courses.add(course);
        courseCsvLoader.saveCsv(courses, Course::serialize);
        }
	public List<Course> getCourses() {
		return courses;
	}
}
