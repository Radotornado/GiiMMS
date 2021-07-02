function validateForm() {
    // get value and trim the whitespaces
    const username = document.getElementById('username').value.trim();

    // check if username is between 3 and 20 chars
    if (username.length < 3 || username.length > 20) {
        setError("Username must be between 3 and 20 characters.")
        return false;
    }
}

function setError(message) {
    document.getElementById("error").textContent = message;
}