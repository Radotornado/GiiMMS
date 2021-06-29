const form = document.getElementById('form');
const firstName= document.getElementById('firstName');
const email = document.getElementById('email');
const password = document.getElementById('password');
const lastName = document.getElementById('lastName');
const Position = document.getElementById('position');

form.addEventListener('submit', e => {
	e.preventDefault();
	
	checkInputs();
});

function checkInputs() {
	// trim to remove the whitespaces
	const firstNameValue = firstName.value.trim();
	const emailValue = email.value.trim();
	const PasswordValue = password.value.trim();
	const lastNameValue =lastName.value.trim();
	const Position = position.value.trim();

	if(firstNameValue === '') {
		setErrorFor(firstName, 'Firstname cannot be blank');
	} else {
		setSuccessFor(firstName);
	}
	
	if(emailValue === '') {
		setErrorFor(email, 'Email cannot be blank');
	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'Not a valid email');
	} else {
		setSuccessFor(email);
	}
	
	if(PasswordValue === '') {
		setErrorFor(password, 'Password cannot be blank');
	} else {
		setSuccessFor(Password);
	}
	
	if(lastNameValue === '') {
		setErrorFor(lastName, 'Lastname cannot be blank');
	} else {
		setSuccessFor(lastName);
	}
	
	if(Position === '') {
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
	
function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}