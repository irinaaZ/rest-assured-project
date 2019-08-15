/*
6. Create a separate class to represent a user. This class should have all the fields according to the mock service.
*/

public class User {
    public int age;
    public String email;
    public String LastName;
    public String FirstName;
    public int id;

    public User(int age, String email, String lastName, String firstName, int id) {
        this.age = age;
        this.email = email;
        this.LastName = lastName;
        this.FirstName = firstName;
        this.id = id;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{age=" + age + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", LastName='" + LastName + '\'' + "\n" +
                ", FirstName='" + FirstName + '\'' + "\n" +
                ", id=" + id + "\n" +
                '}' + "\n";
    }
}