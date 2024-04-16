window.addEventListener('load', () => {
    let url = window.location.href;
    fetch(url)
        .then(response => {
            if (response.ok) {
                setInterval(() => window.location.reload(), 5000)
            }
        })
})

const form = document.getElementById('form');

function chatHandler(e) {
    let urlParams = new URLSearchParams(window.location.search);
    let param = urlParams.get('toUser');

    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);

    let json = JSON.stringify(Object.fromEntries(data));

    fetch(`/chat/send?toUser=${param}`, {
        method: 'POST',
        body: json
    })
}

form.addEventListener('submit', chatHandler)