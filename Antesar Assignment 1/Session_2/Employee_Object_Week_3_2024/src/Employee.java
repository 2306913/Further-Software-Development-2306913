public class Employee {
    private String forename;
    private String surname;
    private final int idNumber;
    private String jobTitle;
    private String jobDescription;
    private String email;
    private String phoneNumber;
    private double salary;

    // Constructor for required fields
    public Employee(String forename, String surname, int idNumber) {
        this.forename = forename;
        this.surname = surname;
        this.idNumber = idNumber;
    }

    // Additional overloaded constructor for all fields
    public Employee(String forename, String surname, int idNumber, String jobTitle, String jobDescription, String email, String phoneNumber, double salary) {
        this.forename = forename;
        this.surname = surname;
        this.idNumber = idNumber;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    // Setter functions
    public void setForename (String forename) {
        this.forename = forename;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public void setJobTitle (String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription (String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSalary (double salary) {
        this.salary = salary;
    }


    // Getter Functions
    public String getForename() {
        return this.forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public double getSalary() {
        return this.salary;
    }


}
