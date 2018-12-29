;(() => {
    // genera errore se viene chiamata una variabile globale
    "use strict";
    let submitDisabled = false;
    let passwordInput = document.querySelector('#password');
    let passwordCheckInput = document.querySelector('#passwordCheck');

    function notifyWrongPassword(isWrong) {
        if (isWrong) {
            passwordInput.classList.add("is-invalid");
            passwordCheckInput.classList.add("is-invalid");
        } else {
            passwordInput.classList.remove("is-invalid");
            passwordCheckInput.classList.remove("is-invalid");
        }
    }

    let passwdInputHandler = (e) => {
        document.querySelector('.btn.btn-primary').disabled = false;
        if (passwordInput.value !== "")
            if (passwordInput.value.localeCompare(passwordCheckInput.value) === 0) {
                submitDisabled = false;
                notifyWrongPassword(false);
            }
            else {
                submitDisabled = true;
                notifyWrongPassword(false);
            }
        else document.querySelector('.btn.btn-primary').disabled = true;
    };

    let submitHandler = (e) => {
        if (submitDisabled) {
            e.preventDefault();
            notifyWrongPassword(true);
        }
    };
// primo che trova

    //passwordCheckInput.addEventListener('keyup', passwdInputHandler, false);
    passwordCheckInput.addEventListener('change', passwdInputHandler, false);
   // passwordInput.addEventListener('keyup', passwdInputHandler, false);
    passwordInput.addEventListener('change', passwdInputHandler, false);

    document.querySelector('.btn.btn-primary').addEventListener('click', submitHandler, false);

    /*
    let buttonHandler = (e) => {
        switch (e.target.dataset.content) {
            case "pippo":
                console.log('%c premuto ' + e.target.dataset.content, 'background:orange;color:white');
                break;
            case "pluto":
                console.log('%c premuto ' + e.target.dataset.content, 'background:green;color:white');
                break;
            case "topolino":
                console.log('%c premuto ' + e.target.dataset.content, 'background:blue;color:white');
                break;
            default:
                console.log('%c errore, nessun evento associato al content type' + 'background:red;color:white');
                break;
        }
    }
    // draggable hammer,js nteract.js touch
    document.querySelector('table')
        .querySelectorAll('button')
        .forEach(button => {
            button.addEventListener('click', buttonHandler, false);
        });
    */
})
();