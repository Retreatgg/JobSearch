<#import "../layot.ftlh" as main>
<@main.layout>
    <div class="main-body mt-5">

        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <#if user.avatar??>
                                <img src="${user.avatar}" alt="User Avatar" class="rounded-circle" width="150">
                            </#if>
                            <div class="mt-3">
                                <h4>${user.name}</h4>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <a class="btn btn-info " target="__blank" href="profile/edit">Edit</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <#if user.accountType == "Applicant">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Full Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.name} ${user.surname}
                                </div>
                            </div>
                        <#else>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Company name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.name} ${user.surname}
                                </div>
                            </div>
                        </#if>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.phoneNumber}
                            </div>
                        </div>
                        <#if user.accountType == "Applicant">
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.age}
                                </div>
                            </div>
                            <#else>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Company age</h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        ${user.age}
                                    </div>
                                </div>
                        </#if>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Account type</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.accountType}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <#if user.accountType == "Employer">
            <div class="row">
                <#list vacancies as vacancy>
                    <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                        <div class="card border-0 bg-light rounded shadow">
                            <div class="card-body p-4">
                                <h5>${vacancy.name}</h5>
                                <div class="mt-3">
                                    <span class="text-muted d-block">${vacancy.description}</span>
                                </div>

                                <div class="mt-3">
                                    <a href="#" class="btn btn-primary">Update</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <#else>
                <div class="row">
                    <#list resumes as resume>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow">
                                <div class="card-body p-4">
                                    <h5>${resume.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block">${resume.description}</span>
                                    </div>

                                    <div class="mt-3">
                                        <a href="#" class="btn btn-primary">Update</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
        </#if>
    </div>
</@main.layout>

