function validateForm() {
    // get value and trim the whitespaces
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const position = document.getElementById('position').value.trim();
    const data = [username, password, firstName, lastName, position];
    const specialChars = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;

    // check if any field is empty
    if (data.some(e => !e)) {
        setError("All fields are required");
        return false;
    } else if (username.length > 21 || username.length < 3
        || firstName.length > 21 || firstName.length < 3
        || lastName.length > 21 || lastName.length < 3) {
        setError("Username, given and family name must be between 3 and 20 chars.");
        return false;
    } else if (password.length > 21 || password.length < 6) {
        setError("Password must be between 6 and 20 characters.");
        return false;
    } else if (!password.match(specialChars)) {
        setError("Password must contain at least one special character.");
        return false;
    } else {
        document.forms['addEmployeeForm'].submit();
    }
}

function setError(message) {
    document.getElementById("error").textContent = message;
}