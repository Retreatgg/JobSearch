<#import "../layot.ftlh" as main>

<@main.layout script="">
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-4">
                <div class="card border-0 shadow">
                    <div class="card-body text-center">
                        <#if user.avatar??>
                            <img src="/images/download/${user.email}" alt="User Avatar" class="rounded-circle mb-3" style="width: 150px; height: 150px;">
                        </#if>
                        <h4 class="mb-0">${user.name} ${user.surname}</h4>
                    </div>
                    <hr class="my-4">
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card border-0 shadow">
                    <div class="card-body">
                        <form action="/profile/edit" method="POST" enctype="multipart/form-data">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            </#if>
                            <div class="mb-3">
                                <label for="inputName" class="form-label">Name</label>
                                <input type="text" class="form-control" id="inputName" name="name">
                            </div>
                            <div class="mb-3">
                                <label for="inputSurname" class="form-label">Surname</label>
                                <input type="text" class="form-control" id="inputSurname" name="surname">
                            </div>
                            <div class="mb-3">
                                <label for="inputPassword" class="form-label">Password</label>
                                <input type="password" class="form-control" id="inputPassword" name="password">
                            </div>
                            <div class="mb-3">
                                <label for="inputPhoneNumber" class="form-label">Phone</label>
                                <input type="text" class="form-control" id="inputPhoneNumber" name="phoneNumber">
                            </div>
                            <div class="mb-3">
                                <#if user.accountType == "APPLICANT">
                                    <label for="inputAge" class="form-label">Age</label>
                                <#else>
                                    <label for="inputCompanyAge" class="form-label">Company age</label>
                                </#if>
                                <input type="number" class="form-control" id="inputAge" name="age">
                            </div>
                            <div class="mb-3">
                                <label for="inputAvatar" class="form-label">Upload avatar</label>
                                <input type="file" class="form-control" id="inputAvatar" name="avatar">
                            </div>
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
