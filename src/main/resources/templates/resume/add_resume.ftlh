<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">Add resume</h5>
                    <form id="form" action="/resumes/add" method="post">
                        <div class="mb-3">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                        <div class="mb-3">
                            <label for="accountType" class="form-label">Select category</label>
                            <select class="form-select" aria-label="Default select example" name="category">
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="number" class="form-control" id="salary" name="salary">
                        </div>

                        <div id="moreInfo"></div>

                        <div class="row mt-3 mb-3">
                            <div class="col-sm-4">
                                <button id="workExp" type="button">ADD WORK EXPERIENCE INFO</button>
                            </div>

                            <div class="col-sm-4">
                                <button id="educInfo" type="button">ADD EDUCATION INFO</button>
                            </div>

                            <div class="col-sm-4">
                                <button id="contactsInfo" type="button">ADD CONTACTS</button>
                            </div>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Add resume</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        const btn = document.getElementById('workExp');
        const form = document.getElementById('form');
        const more = document.getElementById('moreInfo');

        function addRemoveButton(parent, element) {
            const removeButton = document.createElement('button');
            removeButton.textContent = 'Remove';
            removeButton.className = 'btn btn-danger';
            removeButton.type = 'button';
            removeButton.addEventListener('click', () => {
                parent.removeChild(element);
                parent.removeChild(removeButton);
            });
            parent.appendChild(removeButton);
        }

        btn.addEventListener("click", () => {
            let div = document.createElement('div');
            div.className = "mb-3"

            div.innerHTML = `<h5 class="text-center mt-3">Add work experience</h5>
                            <label for="years" class="form-label">Years</label>
                            <input type="number" class="form-control" id="years" name="years">

                            <label for="companyName" class="form-label">Company name</label>
                            <input type="text" class="form-control" id="companyName" name="companyName">

                            <label for="position" class="form-label">Position</label>
                            <input type="text" class="form-control" id="position" name="position">

                            <label for="responsibilities" class="form-label">Responsibilities</label>
                            <input type="text" class="form-control" id="responsibilities" name="responsibilities">
                       `

            form.insertBefore(div, more.nextSibling);
            addRemoveButton(form, div)
        })

        const educInfoBtn = document.getElementById('educInfo')
        educInfoBtn.addEventListener("click", () => {
            let divEduc = document.createElement('div');
            divEduc.className = "mb-3"
            divEduc.innerText = 'Add education info'

            divEduc.innerHTML = `<h5 class="text-center mt-3">Add education info</h5>

                                <label for="institution" class="form-label">Institution</label>
                                <input type="text" class="form-control" id="institution" name="institution">


                                <label for="program" class="form-label">Program</label>
                                <input type="text" class="form-control" id="program" name="program">

                                <label for="degree" class="form-label">Degree</label>
                                <input type="text" class="form-control" id="degree" name="degree">

                                <div id="datetimepicker1" class="input-append date">
                                    <label for="startDate" class="form-label">Start date</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>

                                <div id="datetimepicker1" class="input-append date">
                                    <label for="endDate" class="form-label">End date</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>`

            form.insertBefore(divEduc, more.nextSibling);
            addRemoveButton(form, divEduc)
        })

         let contactBtn = document.getElementById('contactsInfo');

        contactBtn.addEventListener('click', () => {
            let contactDiv = document.createElement('div');
            contactDiv.className = "mb-3"
            contactDiv.innerText = 'Add contacts'

            contactDiv.innerHTML = `
                            <h5 class="text-center mt-3">Add contacts</h5>
                            <label for="accountType" class="form-label">Select contact</label>
                            <select class="form-select" aria-label="Default select example" name="typeId">
                                <#list contacts as contact>
                                    <option value="${contact.id}">${contact.type}</option>
                                </#list>
                            </select>

                            <label for="value" class="form-label">Value</label>
                            <input type="text" class="form-control" id="value" name="value">
`

            form.insertBefore(contactDiv, more.nextSibling);
            addRemoveButton(form, contactDiv)
        })

    </script>
</@main.layout>