package com.arda.GoogleContacts.dto;

import java.util.List;

public class UpdateContactRequest {
    // Field to store the unique identifier of the contact
    private String resourceName;
    // Field to store the first name of the contact
    private String firstName;
    // Field to store the last name of the contact
    private String lastName;
    // Field to store the list of email addresses of the contact
    private List<String> emails;
    // Field to store the list of phone numbers of the contact
    private List<String> phoneNumbers;

    // Getter for resourceName
    public String getResourceName() {
        return resourceName;
    }

    // Setter for resourceName
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    // Getter for firstName
    public String getFirstName() {
        return firstName;
    }

    // Setter for firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for lastName
    public String getLastName() {
        return lastName;
    }

    // Setter for lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter for emails
    public List<String> getEmails() {
        return emails;
    }

    // Setter for emails
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    // Getter for phoneNumbers
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    // Setter for phoneNumbers
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}