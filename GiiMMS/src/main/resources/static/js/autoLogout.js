function autoLogout() {
    let timer;

    // check for any movement
    window.onload = resetTimer;
    window.onmousemove = resetTimer;
    window.onclick = resetTimer;
    window.onkeypress = resetTimer;

    // clear timer or set it to push the logout button after
    // 2 minutes of inactivity
    function resetTimer() {
        clearTimeout(timer);
        timer = setTimeout(function () {
            document.getElementById('logoutForm').submit();
        }, 120000);
    }
}