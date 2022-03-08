import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Administrator {
	private List<CourseList> courseRecord;
    private List<Person> people;
    private List<Course> courses;
    private List<User> users;
    private final CsvLoader courseListCsvLoader = new CsvLoader("courseList.csv", "utf-8");
    private final CsvLoader personCsvLoader = new CsvLoader("person.csv", "utf-8");
    private final CsvLoader courseCsvLoader = new CsvLoader("course.csv", "utf-8");
    private final CsvLoader userCsvLoader = new CsvLoader("user.csv", "utf-8");
    private final Scanner input = new Scanner(System.in);

    public void input() {
        System.out.println("course name: " + courseNames());
        System.out.print("input course name: ");
        int courseNo = input.nextInt();
        System.out.println("apply name: " + personNames());
        System.out.print("input confirm name (split with \",\"): ");
        String confirmNo = input.next();
        confirmPerson(courseNo, confirmNo);
        courseListCsvLoader.saveCsv(courseRecord, CourseList::serialize);
    }

    private void confirmPerson(int courseNo, String confirmNo) {
        String[] confirmPersonNo = confirmNo.split(",");
        List<Person> confirmPerson = new ArrayList<>();
        for (String no : confirmPersonNo) {
            Person person = people.get(Integer.parseInt(no) - 1);
            confirmPerson.add(person);
        }
        for (CourseList record : courseRecord) {
            if (record.matchCourseName(courses.get(courseNo - 1).getName())) {
                record.setConfirmPerson(confirmPerson);
                return;
            }
        }
        CourseList courseList = new CourseList();
        courseList.setCourse(courses.get(courseNo - 1));
        courseList.setConfirmPerson(confirmPerson);
        courseRecord.add(courseList);
    }
    
    public void init() {
        people = personCsvLoader.loadCsv(line -> {
            Person person = new Person();
            person.setName(line[0]);
            person.setMajor(line[1]);
            person.setAge(Integer.parseInt(line[2]));
            return person;
        });
        courses = courseCsvLoader.loadCsv(line -> {
            Course course = new Course();
            course.setName(line[0]);
            course.setRequirements(line[1]);
            course.setTime(line[2]);
            course.setLocation(line[3]);
            return course;
        });;
        courseRecord = courseListCsvLoader.loadCsv(line -> {
            CourseList courseList = new CourseList();
            courseList.setCourse(findCourseByName(line[0]));
            List<Person> confirmPerson = new ArrayList<>();
            for (int i = 1; i < line.length; ++i) {
                confirmPerson.add(findPersonByName(line[i]));
            }
            courseList.setConfirmPerson(confirmPerson);
            return courseList;
        });
        users = userCsvLoader.loadCsv(line -> {
        	User user = new User();
        	user.setUsername(line[0]);
        	user.setPassword(line[1]);
        	user.setRole(Integer.parseInt(line[2]));
        	return user;
        });
    }
    
    private Course findCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    private Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
    
    private String courseNames() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < courses.size(); i++) {
            joiner.add(1 + i + "." + courses.get(i).getName());
        }
        return joiner.toString();
    }

    private String personNames() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < people.size(); i++) {
            joiner.add((1 + i) + "." + people.get(i).getName());
        }
        return joiner.toString();
    }

	public List<CourseList> getCourseRecord() {
		return courseRecord;
	}

	public void setCourseRecord(List<CourseList> courseRecord) {
		this.courseRecord = courseRecord;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public CsvLoader getCourseListCsvLoader() {
		return courseListCsvLoader;
	}

	public CsvLoader getPersonCsvLoader() {
		return personCsvLoader;
	}

	public CsvLoader getCourseCsvLoader() {
		return courseCsvLoader;
	}

	public CsvLoader getUserCsvLoader() {
		return userCsvLoader;
	}
}
