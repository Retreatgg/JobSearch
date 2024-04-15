let form = document.getElementById('form');


function resumeAddHandler(e) {
    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);
    let json = JSON.stringify(Object.entries(form))

    fetch('/resumes/add', {
        method: 'POST',
        body: json
    })
}

form.addEventListener('submit', resumeAddHandler);
