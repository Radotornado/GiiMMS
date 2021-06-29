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


    if (firstNameValue === '') {
        setErrorFor(firstName, 'Firstname cannot be blank');

    } else {
        setSuccessFor(firstName);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'Password cannot be blank');
    } else {
        setSuccessFor(Password);
    }

    if (lastNameValue === '') {
        setErrorFor(lastName, 'Lastname cannot be blank');
    } else {
        setSuccessFor(lastName);
    }

    if (positionValue === '') {
        setErrorFor(position, 'Position cannot be blank');
    } else {
        setSuccessFor(Position);
    }
    if (usernameValue === '') {
            setErrorFor(username, 'Username cannot be blank');
        } else {
            setSuccessFor(Username);
        }
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