# Google Contacts/People API Integration - Spring Boot Application

## Objective

The objective of this activity is to integrate the **Google Contacts (People) API** into a **Spring Boot application** with a **Thymeleaf-based user interface**. The application allows authenticated users to retrieve, view, and manage their Google Contacts directly from the web application. This activity provided hands-on experience with **OAuth 2.0 authentication**, **RESTful API consumption**, and **building CRUD functionality** in a real-world context.

---

## Features

- 🔐 **OAuth 2.0 Authentication** using Google Login.
- 📇 **Retrieve Google Contacts** from the authenticated user's account.
- ➕ **Add new contacts** directly from the web interface.
- ✏️ **Edit existing contacts** using form-based input.
- ❌ **Delete contacts** with a simple action.
- 🖥️ **Thymeleaf UI** for intuitive user interaction.
- ⚠️ **Error handling** for API request failures, authentication errors, and missing permissions.

---

## Technologies Used

- **Spring Boot** (Backend Framework)
- **Spring Security OAuth2 Client** (Google OAuth Integration)
- **Google People API** (Contacts Management API)
- **Thymeleaf** (Frontend Template Engine)
- **Maven** (Build and Dependency Management)

---

## Instructions

### 1. Google Cloud Setup

- Created a **Google Cloud project**.
- Enabled the **People API**.
- Created **OAuth 2.0 Credentials** (Client ID & Client Secret).
- Configured the **OAuth Consent Screen** with required scopes:
    - `https://www.googleapis.com/auth/contacts`
- Added `http://localhost:8080/contacts` as an **Authorized Redirect URI**.

---

### 2. Backend Development (Spring Boot)

- Implemented **Google OAuth 2.0 Authentication Flow** using `spring-security-oauth2-client`.
- Developed **REST endpoints** for:
    - Fetching all contacts from Google People API.
    - Creating new contacts.
    - Editing existing contacts.
    - Deleting contacts.
- Integrated **Google People API Client Library** to handle requests.

---

### 3. Frontend Development (Thymeleaf UI)

- Developed a **Thymeleaf-based dashboard** to display the contact list.
- Created forms for **adding and editing** contacts.
- Provided action buttons for **updating and deleting** contacts.
- Ensured UI usability with clear buttons, form inputs, and confirmation messages.

---

### 4. Testing

- Ran the application using VScode:

- Verified:
    - Successful Google authentication.
    - Display of contacts list.
    - Full **CRUD functionality**.
    - Proper error messages when requests fail.
- Tested with various user accounts to ensure smooth handling of permissions and errors.

---

## Expected Output

- Fully functional **Spring Boot web application**.
- Users can:
    - Log in with Google.
    - View, add, edit, and delete their **Google Contacts**.
- Smooth integration between **Spring Boot backend**, **Google People API**, and **Thymeleaf UI**.

---

## Challenges Encountered

- Configuring the **OAuth Consent Screen** and ensuring correct scopes were granted.
- Managing **OAuth token refresh flow** to handle long-running sessions.
- Handling errors such as **quota limits, invalid scopes, and missing contact permissions**.
- Mapping **Google People API response data** into simpler UI-friendly formats.

---
