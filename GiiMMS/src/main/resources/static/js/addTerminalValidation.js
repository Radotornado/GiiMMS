function validateTerminalForm() {
    // get value and trim the whitespaces
    const branchName = document.getElementById('branchName').value.trim();
    const latitude = document.getElementById('latitude').value.trim();
    const longitude = document.getElementById('longitude').value.trim();
    const digit = /^\d+.\d+$/;

    // check if username is between 3 and 20 chars
    if (branchName.length < 3 || branchName.length > 20) {
        setError("Branch name must be between 3 and 20 characters.")
        return false;
    } else if (latitude.length < 3 || latitude.length > 20) {
        setError("Latitude must be between 3 and 20 characters.")
        return false;
    } else if (longitude.length < 3 || longitude.length > 20) {
        setError("Longitude must be between 3 and 20 characters.")
        return false;
    } else if (!latitude.match(digit)) {
        setError("Latitude must be a digit, separated by a dot.")
        return false;
    }else if (!longitude.match(digit)) {
        setError("Longitude must be a digit, separated by a dot.")
        return false;
    } else {
        document.forms['addTerminalForm'].submit();
    }
}

function setError(message) {
    document.getElementById("error").textContent = message;
}