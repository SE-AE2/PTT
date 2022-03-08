import java.util.Scanner;
public class Main {
	static Administrator administrator;
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		administrator = new Administrator();
		administrator.init();
		login();
	}
	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Please input your username:");
		String username = scanner.nextLine();
		System.out.println("Please input your password:");
		String password = scanner.nextLine();
		for(User user: administrator.getUsers())
		{
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				switch (user.getRole()) {
				//Administrator
				case 1: 
				{
					System.out.println("Your role is: Administrator.");
					break;
				}
				//Class Director	
				case 2:
				{
					ClassDirector classDirector = new ClassDirector();
					System.out.println("Your role is: Class Director.");
					System.out.println("1. View course list.");
					System.out.println("2. Add course");
					System.out.println("3. Log out");
					System.out.print("Your choice: ");
					int choice = scanner.nextInt();
					while (choice > 3 || choice < 1) {
						System.out.println("Incorrect choice, Please try again!");
						System.out.print("Your Choice: ");
						choice = scanner.nextInt();
					}
					switch (choice) {
					case 1: {
						System.out.println("Name, Requirements, Time, Location");
						for(Course course: classDirector.getCourses())
						{
							System.out.println(course.toString());
						}
						break;
					}
					
					case 2:{
						classDirector.input();
						for(Course course: classDirector.getCourses())
						{
							System.out.println(course.toString());
						}
						break;
					}
					case 3:{
						login();
						break;
					}
					}
					break;
				}
				//PTT(Person)
				case 3:
				{
					System.out.println("Your role is: PTT.");
					break;
				}
				}			
			}
		}
	}
}
