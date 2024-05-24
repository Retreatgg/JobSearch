<#import "../layot.ftlh" as main>

<@main.layout script="" ; spring>
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4"><@spring.message "user.create.form"/></h5>
                    <form id="registrationForm" action="/auth/register" method="post" enctype="multipart/form-data">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>

                        <div class="mb-3">
                            <label for="role" class="form-label"><@spring.message "user.choose.role"/></label>
                            <select class="form-select" id="role" name="role" onchange="updateForm()">
                                <option name="accountType" value="user"><@spring.message "user.choose.applicant"/></option>
                                <option name="accountType" value="company"><@spring.message "user.choose.company"/></option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="name" class="form-label" id="usernameLabel"><@spring.message "user.create.your.name"/></label>
                            <@spring.formInput "userDto.name" 'id="name" class="form-control form-control-lg" value="${userDto.name!""}"'/>
                        </div>

                        <input type="hidden" id="accountType" name="accountType" value="APPLICANT">

                        <div class="mb-3" id="applicantSurname">
                            <label for="surname" class="form-label"><@spring.message "user.create.your.surname"/></label>
                            <@spring.formInput "userDto.surname" 'id="surname" class="form-control form-control-lg" value="${userDto.surname!""}"'/>
                        </div>

                        <div class="mb-3">
                            <label for="age" id="age" class="form-label"><@spring.message "user.create.your.age"/></label>
                            <@spring.formInput "userDto.age" 'id="age" class="form-control form-control-lg" value="${userDto.age!""}"'/>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label"><@spring.message "user.create.your.email"/></label>
                            <@spring.formInput "userDto.email" 'id="email" class="form-control form-control-lg" value="${userDto.email!""}"'/>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label"><@spring.message "user.create.your.password"/></label>
                            <@spring.formPasswordInput "userDto.password" 'id="password" class="form-control form-control-lg" value="${userDto.password!""}"'/>
                        </div>

                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label"><@spring.message "user.create.your.phone.number"/></label>
                            <@spring.formInput "userDto.phoneNumber" 'id="phoneNumber" class="form-control form-control-lg" value="${userDto.phoneNumber!""}"'/>
                        </div>

                        <div class="mb-3">
                            <label for="profilePicture" class="form-label"><@spring.message "user.create.your.photo"/></label>
                            <input type="file" class="form-control" id="profilePicture" name="photo">
                            <@spring.showErrors ', ' 'error'/>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"><@spring.message "user.create.btn"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        const div = document.getElementById('applicantSurname');
        function updateForm() {
            let accountType = document.getElementById('accountType');

            const role = document.getElementById('role').value;
            const age = document.getElementById('age');

            const usernameLabel = document.getElementById('usernameLabel');
            if (role === 'company') {
                accountType.value = 'EMPLOYER';
                usernameLabel.textContent = '<@spring.message "user.create.your.company.name"/>';
                toggleDiv()
                age.textContent = '<@spring.message "user.create.your.company.age"/>'
            } else {
                accountType.value = 'APPLICANT';
                usernameLabel.textContent = '<@spring.message "user.create.your.name"/>';
                showDiv()
                age.textContent = '<@spring.message "user.create.your.age"/>'
            }
        }

        function toggleDiv() {
            div.classList.add('hidden')
            div.style.display = 'none'
        }

        function showDiv() {
            div.style.display = 'block'
            div.classList.remove('hidden')
        }
    </script>
</@main.layout>
