let form = document.getElementById('form');

function vacancyAddHandler(e) {
    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);

    let json = JSON.stringify(Object.fromEntries(data));

    fetch('/vacancies/add', {
        method: 'POST',
        body: json
    })
}

form.addEventListener('submit', vacancyAddHandler);
