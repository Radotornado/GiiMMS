const form = document.getElementById('addEmployeeForm');
const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
const password = document.getElementById('password');
const position = document.getElementById('position');
const username = document.getElementById('username');

function validateForm() {

    // trim to remove the whitespaces
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const passwordValue = password.value.trim();
    const positionValue = position.value.trim();
    const usernameValue = username.value.trim();

    if (!firstNameValue) {
        setErrorFor(firstName, 'Firstname cannot be blank');
        return false;
    } else {
        setSuccessFor(firstName);
    }

    if (!passwordValue) {
        setErrorFor(password, 'Password cannot be blank');
        return false;
    } else {
        setSuccessFor(password);
    }

    if (!lastNameValue) {
        setErrorFor(lastName, 'Lastname cannot be blank');
        return false;
    } else {
        setSuccessFor(lastName);
    }

    if (!positionValue) {
        setErrorFor(position, 'Position cannot be blank');
        return false;
    } else {
        setSuccessFor(position);
    }
    if (!usernameValue) {
        setErrorFor(username, 'Username cannot be blank');
        return false;
    } else {
        setSuccessFor(username);
    }
    document.forms['addEmployeeForm'].submit();
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}