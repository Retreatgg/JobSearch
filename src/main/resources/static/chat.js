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

    fetch(`/chat/send?toUser=${param}`, {
        method: 'POST',
        body: json
    })
}

form.addEventListener('submit', chatHandler)