import java.util.StringJoiner;

public class Course {

    private String name;

    private String requirements;

    private String time;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", requirements='" + requirements + '\'' +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
    
    public String serialize() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(name);
        joiner.add(requirements);
        joiner.add(time);
        joiner.add(location);
        return joiner.toString();
    }
}
