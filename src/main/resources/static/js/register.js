;(() => {
    // genera errore se viene chiamata una variabile globale
    "use strict";
    let inputCheck = {
        username: false,
        name: false,
        password: false
    }
    let username = document.querySelector('#username');

    let nameInput = document.querySelector('#name');
    let surnameInput = document.querySelector('#surname');

    let passwordInput = document.querySelector('#password');
    let passwordCheckInput = document.querySelector('#passwordCheck');


    var not_created = true;

    function createSubmit() {
        if (inputCheck.username && inputCheck.password && inputCheck.name) {
            console.log("You can submit");
            if (not_created) {
                var button = document.createElement("button");
                button.setAttribute("class", "btn btn-primary");
                button.setAttribute("type", "submit");
                button.innerHTML = ("submit");
                document.getElementById("submitSpace").appendChild(button);
                not_created = false;
            }
            return;
        }
        not_created = true;
        var myNode = document.getElementById("submitSpace");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
        console.log("You can't submit")
    }

    function notifyWrongPassword(isWrong) {
        if (isWrong) {
            passwordInput.classList.add("is-invalid");
            passwordCheckInput.classList.add("is-invalid");
            inputCheck.password = false;
        } else {
            passwordInput.classList.remove("is-invalid");
            passwordCheckInput.classList.remove("is-invalid");
            inputCheck.password = true;
        }
        createSubmit();
    }

    function notifyWrongName(isWrong) {
        if (isWrong) {
            nameInput.classList.add("is-invalid");
            surnameInput.classList.add("is-invalid");
            inputCheck.name = false;
        } else {
            nameInput.classList.remove("is-invalid");
            surnameInput.classList.remove("is-invalid");
            inputCheck.name = true;
        }
        createSubmit();
    }

    function notifyWrongUsername(isWrong) {
        if (isWrong) {
            username.classList.add("is-invalid");
            inputCheck.username = false;
        } else {
            username.classList.remove("is-invalid");
            inputCheck.username = true;
        }
        createSubmit();
    }

    // name CHECK
    let nameInputHandler = (e) => {
        let myRe = /^[a-z]+$/i;
        console.log(nameInput.value + " " + myRe.test(nameInput.value) + " " + surnameInput.value + " "
            + myRe.test(surnameInput.value))
        if (myRe.test(nameInput.value) && myRe.test(surnameInput.value)) {
            notifyWrongName(false);
        } else {
            notifyWrongName(true);
        }
    };

    // USERNAME CHECK
    let usernameInputHandler = (e) => {
        let myRe = /^[a-zA-Z0-9_]+$/i;
        if (myRe.test(username.value)) {
            notifyWrongUsername(false);
        } else {
            notifyWrongUsername(true);
        }
    };
    // PASSWORD CHECK
    let passwdInputHandler = (e) => {
        console.log("usernameInputHandler");
        console.log(passwordInput.value.length)
        if (passwordInput.value.length >= 8 && passwordInput.value.length <= 15) {
            if (passwordInput.value.localeCompare(passwordCheckInput.value) === 0) {
                notifyWrongPassword(false);
            }
            else {
                notifyWrongPassword(true);
            }
        } else {
            notifyWrongPassword(true);
        }
    };

// primo che trova

    passwordCheckInput.addEventListener('keyup', passwdInputHandler, false);
    passwordCheckInput.addEventListener('change', passwdInputHandler, false);
    passwordInput.addEventListener('keyup', passwdInputHandler, false);
    passwordInput.addEventListener('change', passwdInputHandler, false);

    surnameInput.addEventListener('keyup', nameInputHandler, false);
    surnameInput.addEventListener('change', nameInputHandler, false);
    nameInput.addEventListener('keyup', nameInputHandler, false);
    nameInput.addEventListener('change', nameInputHandler, false);

    username.addEventListener('keyup', usernameInputHandler, false);
    username.addEventListener('change', usernameInputHandler, false);
    //document.querySelector('.btn.btn-primary').addEventListener('click', submitHandler, false);

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