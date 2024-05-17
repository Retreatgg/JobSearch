<#import "../layot.ftlh" as main>

<@main.layout script="">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">Add resume</h5>
                    <form id="form" action="/resumes/add" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <div class="mb-3">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label">Select category</label>
                            <select class="form-select" aria-label="Default select example" name="categoryName">
                                <option value="">Categories</option>
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="number" class="form-control" id="salary" name="salary">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="checkbox" value="true"
                                       aria-label="Checkbox for following text input" name="isActive">
                                <span>Is Active</span>
                            </div>
                            <input type="text" hidden="hidden" class="form-control" aria-label="Is active" name="isActive">
                        </div>

                        <h5 class="text-center mt-3">Add contacts</h5>
                        <#assign index = 0>
                        <#list contacts as contact>
                            <input type="hidden" class="form-control" id="contacts[${index}].type" name="contacts[${index}].type" value="${contact.type}">
                            <label for="contacts[${index}].value" class="form-label">${contact.type}</label>
                            <#if contact.type == "Email">
                                <input type="email" class="form-control" id="contacts[${index}`].value" name="contacts[${index}].value">
                            <#else>
                                <input type="text" class="form-control" id="contacts[${index}`].value" name="contacts[${index}].value">
                            </#if>
                            <#assign index++>
                        </#list>

                        <div id="moreInfo"></div>

                        <div class="row mt-3 mb-3">
                            <div class="col-sm-4">
                                <button id="workExp" type="button" class="btn btn-primary">ADD WORK EXPERIENCE INFO</button>
                            </div>

                            <div class="col-sm-4">
                                <button id="educInfo" type="button" class="btn btn-primary">ADD EDUCATION INFO</button>
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
        let educIndex = -1;
        let workIndex = -1;

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
            workIndex++

            div.innerHTML = `<h5 class="text-center mt-3">Add work experience</h5>
                            <label for="workExperienceInfo[`+workIndex+`].years" class="form-label">Years</label>
                            <input type="number" class="form-control" id="workExperienceInfo[`+workIndex+`].years" name="workExperienceInfo[`+workIndex+`].years">

                            <label for="workExperienceInfo[`+workIndex+`].companyName" class="form-label">Company name</label>
                            <input type="text" class="form-control" id="workExperienceInfo[`+workIndex+`].companyName" name="workExperienceInfo[`+workIndex+`].companyName">

                            <label for="workExperienceInfo[`+workIndex+`].position" class="form-label">Position</label>
                            <input type="text" class="form-control" id="workExperienceInfo[`+workIndex+`].position" name="workExperienceInfo[`+workIndex+`].position">

                            <label for="workExperienceInfo[`+workIndex+`].responsibilities" class="form-label">Responsibilities</label>
                            <input type="text" class="form-control" id="workExperienceInfo[`+workIndex+`].responsibilities" name="workExperienceInfo[`+workIndex+`].responsibilities">
                       `

            form.insertBefore(div, more.nextSibling);
            addRemoveButton(form, div)
        })

        const educInfoBtn = document.getElementById('educInfo')
        educInfoBtn.addEventListener("click", () => {
            let divEduc = document.createElement('div');
            divEduc.className = "mb-3"
            educIndex++

            divEduc.innerHTML = `<h5 class="text-center mt-3">Add education info</h5>

                                <label for="educationInfo[`+educIndex+`].institution" class="form-label">Institution</label>
                                <input type="text" class="form-control" id="educationInfo[`+educIndex+`].institution" name="educationInfo[`+educIndex+`].institution">

                                <label for="educationInfo[`+educIndex+`].program" class="form-label">Program</label>
                                <input type="text" class="form-control" id="educationInfo[`+educIndex+`].program" name="educationInfo[`+educIndex+`].program">

                                <label for="educationInfo[`+educIndex+`].degree" class="form-label">Degree</label>
                                <input type="text" class="form-control" id="educationInfo[`+educIndex+`].degree" name="educationInfo[`+educIndex+`].degree">

                                <div id="datetimepicker1" class="input-append date">
                                    <label for="educationInfo[`+educIndex+`].startDate" class="form-label">Start date</label>
                                    <input type="date" class="form-control" id="educationInfo[`+educIndex+`].startDate" name="educationInfo[`+educIndex+`].startDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>

                                <div id="datetimepicker1" class="input-append date">
                                    <label for="educationInfo[`+educIndex+`].endDate" class="form-label">End date</label>
                                    <input type="date" class="form-control" id="educationInfo[`+educIndex+`].endDate" name="educationInfo[`+educIndex+`].endDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>`

            form.insertBefore(divEduc, more.nextSibling);
            addRemoveButton(form, divEduc)
        })

    </script>
</@main.layout>

