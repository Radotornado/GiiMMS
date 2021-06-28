const form = document.getElementById('form');
const firstName= document.getElementById('firstName');
const email = document.getElementById('email');
const Password = document.getElementById('Password');
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
	const PasswordValue = Password.value.trim('Password');
	const lastNameValue =lastName.value.trim('lastName');
	const Position = position.value.trim('position');

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
		setErrorFor(PasswordValue, 'Password cannot be blank');
	} else {
		setSuccessFor(Password);
	}
	
	if(lastNameValue === '') {
		setErrorFor(lastName, 'Lastname cannot be blank');
	} else {
		setSuccessFor(lastName);
	}
	
	if(Position === '') {
		setErrorFor(Position, 'Position cannot be blank');
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