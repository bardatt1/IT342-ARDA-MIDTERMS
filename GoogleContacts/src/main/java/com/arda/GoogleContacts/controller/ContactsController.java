package com.arda.GoogleContacts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.arda.GoogleContacts.dto.UpdateContactRequest;
import com.arda.GoogleContacts.service.GoogleContactsService;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final GoogleContactsService googleContactsService;
    private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

    public ContactsController(OAuth2AuthorizedClientService authorizedClientService,
            GoogleContactsService googleContactsService) {
        this.authorizedClientService = authorizedClientService;
        this.googleContactsService = googleContactsService;
    }

    // Helper method to retrieve the OAuth2 access token for the authenticated user
    private String getAccessToken(OAuth2User user) {
        if (user == null) {
            return null;
        }

        // Load the authorized client for the user
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient("google", user.getName());
        if (client == null || client.getAccessToken() == null) {
            return null;
        }

        // Return the access token value
        return client.getAccessToken().getTokenValue();
    }

    // Handle GET requests to the /contacts endpoint
    @GetMapping
    public ResponseEntity<?> getGoogleContacts(@AuthenticationPrincipal OAuth2User user) {
        String accessToken = getAccessToken(user);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please log in.");
        }

        try {
            // Fetch contacts using the access token
            String contacts = googleContactsService.getContacts(accessToken);
            if (contacts == null || contacts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No contacts found.");
            }
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            logger.error("Error fetching contacts", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching contacts: " + e.getMessage());
        }
    }

    // Handle POST requests to the /contacts/add endpoint
    @PostMapping("/add")
    public ResponseEntity<String> addGoogleContact(@AuthenticationPrincipal OAuth2User user,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam List<String> emails,
            @RequestParam List<String> phoneNumbers) {
        String accessToken = getAccessToken(user);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please log in.");
        }

        try {
            // Add contact using the access token and provided details
            String response = googleContactsService.addContact(accessToken, firstName, lastName, emails, phoneNumbers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error adding contact", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding contact: " + e.getMessage());
        }
    }

    // Handle PATCH requests to the /contacts/update endpoint
    @PatchMapping("/update")
    public ResponseEntity<String> updateGoogleContact(@AuthenticationPrincipal OAuth2User user,
            @RequestBody UpdateContactRequest request) {
        String accessToken = getAccessToken(user);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Unauthorized - Please log in.");
        }

        String resourceName = request.getResourceName();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        List<String> emails = request.getEmails();
        List<String> phoneNumbers = request.getPhoneNumbers();

        // Validate request data
        if (resourceName == null || resourceName.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Resource name is required");
        }

        if (firstName == null && lastName == null &&
            (emails == null || emails.isEmpty()) && 
            (phoneNumbers == null || phoneNumbers.isEmpty())) {
            return ResponseEntity.badRequest().body("Error: At least one field to update is required");
        }

        try {
            // Update contact using the access token and provided details
            String response = googleContactsService.updateContact(accessToken, resourceName, firstName, lastName, emails, phoneNumbers);
            if (response.startsWith("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.ok("Contact updated successfully");
        } catch (Exception e) {
            logger.error("Error updating contact", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating contact: " + e.getMessage());
        }
    }

    // Handle DELETE requests to the /contacts/delete endpoint
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteGoogleContact(@AuthenticationPrincipal OAuth2User user,
            @RequestParam String resourceName) {
        String accessToken = getAccessToken(user);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please log in.");
        }

        try {
            // Delete contact using the access token and resource name
            String response = googleContactsService.deleteContact(accessToken, resourceName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error deleting contact", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting contact: " + e.getMessage());
        }
    }

    // Handle GET requests to the /contacts-ui endpoint
    @GetMapping("/contacts-ui")
    public String showContactsPage() {
        return "contacts";
    }
}