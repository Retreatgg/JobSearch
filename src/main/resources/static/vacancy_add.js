let form = document.getElementById('form');

function vacancyAddHandler(e) {
    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);

    let json = JSON.stringify(Object.fromEntries(data));

    let user = restoreUser()
    console.log(user)
    let headers = makeHeaders()

    fetch('/vacancies/add', {
        method: 'POST',
        mode: 'cors',
        body: json,
        headers: headers

    })
}

function makeHeaders (){
    let user = restoreUser()
    console.log(user)
    let headers = new Headers()
    headers.set('Content-Type','application/json')
    if(user){
        headers.set(  'Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
        console.log(headers)
    }
    return headers
}


function restoreUser() {
    return JSON.parse(localStorage.getItem('user'))
}

form.addEventListener('submit', vacancyAddHandler);
