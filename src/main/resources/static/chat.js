window.addEventListener('load', () => {
    setInterval(() => fetch('/chat/open'), 5000);
})

const form = document.getElementById('form');

function chatHandler(e) {
    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);

    let json = JSON.stringify(Object.fromEntries(data));

    fetch('/chat/send', {
        method: 'POST',
        body: json
    })
}

form.addEventListener('submit', chatHandler)