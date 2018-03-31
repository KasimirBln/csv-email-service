package utilities;

public class Contact {
    private String emailAddress;
    private String firstName;
    private String lastName;

    public Contact(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isValid() {
        return firstName != null && !firstName.equals("") &&
                lastName != null && !lastName.equals("") &&
                emailAddress.indexOf('@', 1) > -1 &&
                emailAddress.indexOf('.') > emailAddress.indexOf('@') &&
                emailAddress.indexOf('.') < emailAddress.length() -1;
    }

    public boolean isHeader() {
        return emailAddress.indexOf('@') == -1;
    }

    public String toCsv() {
        return "\"" + this.emailAddress + "\";\"" + this.firstName + "\";\"" + this.lastName + "\";";
    }

    public String toString() {
        return "Contact: email = " + this.emailAddress +
                ", first name = " + this.firstName +
                ", last name = " + this.lastName;
    }
}
