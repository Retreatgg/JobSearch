let mainForm = document.getElementById('form');
let form


function resumeAddHandler(e) {
    e.preventDefault();

    let formData = e.target;
    let data = new FormData(formData);
    let educForm = new FormData(document.getElementById('educ'))
    const educ = {}
    educForm.forEach((value, key) => {
        educ[key] = value;
    })

    const main = {}
    mainForm.forEach((value, key) => {
        main[key] = value
    })

    let expForm = new FormData(document.getElementById('exp'))

    const exp = {}
    expForm.forEach((value, key) => {
        exp[key] = value;
    })

    let contactForm = new FormData(document.getElementById('cont'))
    const contact = {}
    contactForm.forEach((value, key) => {
        contact[key] = value;
    })

    form = {main, educ, exp, contact}

    let json = JSON.stringify(Object.fromEntries(data));

    console.log(json)

    fetch('/resumes/add', {
        method: 'POST',
        body: JSON.stringify(form)
    })
}

mainForm.addEventListener('submit', resumeAddHandler);
