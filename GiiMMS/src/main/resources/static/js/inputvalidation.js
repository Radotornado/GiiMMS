const form = document.getElementById('addEmployeeForm');
const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
const password = document.getElementById('password');
const position = document.getElementById('position');

form.addEventListener('submit', e => {
    e.preventDefault();
    checkInputs();
});

function checkInputs() {
    // trim to remove the whitespaces
    alert(position.value);
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const passwordValue = password.value.trim();
    const position = position.value.trim();
    const username = position.value.trim();

    if (firstNameValue === '') {
        setErrorFor(firstName, 'Firstname cannot be blank');
    } else {
        setSuccessFor(firstName);
    }

    if (emailValue === '') {
        setErrorFor(email, 'Email cannot be blank');
    } else if (!isEmail(emailValue)) {
        setErrorFor(email, 'Not a valid email');
    } else {
        setSuccessFor(email);
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

    if (position === '') {
        setErrorFor(position, 'Position cannot be blank');
    } else {
        setSuccessFor(Position);
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