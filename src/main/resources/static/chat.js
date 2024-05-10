window.addEventListener('load', () => {
    let url = window.location.href;
    fetch(url)
        .then(response => {
            if (response.ok) {
                setInterval(() => window.location.reload(), 5000)
            }
        }).then(sortMessages)

    sortMessages()
})

function sortMessages() {
    const allMessages = document.querySelectorAll('.message');

    const messages = Array.from(allMessages);
    messages.sort((a, b) => {
        const firstMessage = new Date(a.getAttribute('data-send-time'));
        const secondMessage = new Date(b.getAttribute('data-send-time'));
        return firstMessage - secondMessage;
    });

    const print = document.getElementById('chat');
    print.innerHTML = '';

    messages.forEach(message => {
        print.appendChild(message);
    });
}

const form = document.getElementById('form');

function chatHandler(e) {
    let urlParams = new URLSearchParams(window.location.search);
    let param = urlParams.get('toUser');

    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);

    let json = JSON.stringify(Object.fromEntries(data));
    let headers = makeHeaders()

    fetch(`/chat/send?toUser=${param}`, {
        method: 'POST',
        body: json,
        mode: 'cors',
        headers: headers
    })
}

function makeHeaders() {
    let user = restoreUser()
    console.log(user)
    let headers = new Headers()
    headers.set('Content-Type', 'application/json')
    let header = document.head.querySelector("[name~=_csrf_header][content]").content;
    let token = document.head.querySelector("[name~=_csrf_token][content]").content;

    headers.set(header, token)
    if (user) {
        headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
    }
    return headers
}


function restoreUser() {
    return JSON.parse(localStorage.getItem('user'))
}

form.addEventListener('submit', chatHandler)