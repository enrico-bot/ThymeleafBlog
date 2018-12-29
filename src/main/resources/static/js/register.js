;(() => {
    // genera errore se viene chiamata una variabile globale
    "use strict";
    let submitDisabled = false;
    let password = document.querySelector('#password');
    let passwordCheck = document.querySelector('#passwordCheck');
    let handlerTable = (e) => {
        document.querySelector('.btn.btn-primary').disabled = false;
        if (document.querySelector('#password').value !== "")
            if (document.querySelector('#password').value.localeCompare(document.querySelector('#passwordCheck').value) === 0) {
                submitDisabled = false;
                console.log("EQUAL")
                document.querySelector(".invalid-feedback").style.display = "none";
            }
            else {
                submitDisabled = true;
                console.log("NOT EQUAL");
                document.querySelector(".invalid-feedback").style.display = "none";
            }
        else document.querySelector('.btn.btn-primary').disabled = true;
    };
    let submitHandler = (e) => {
        if (submitDisabled) {
            e.preventDefault();
            document.querySelector(".invalid-feedback").style.display = "block";
        }
    };
// primo che trova

    passwordCheck.addEventListener('keyup', handlerTable, false);
    passwordCheck.addEventListener('change', handlerTable, false);
    password.addEventListener('keyup', handlerTable, false);
    password.addEventListener('change', handlerTable, false);

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