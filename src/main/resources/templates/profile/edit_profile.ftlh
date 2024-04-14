<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="main-body">
    <div class="row">
        <div class="col-lg-4">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <#if user.avatar??>
                            <img src="${user.avatar}" alt="User Avatar">
                        </#if>
                            <h4>${user.name} ${user.surname}</h4>
                        </div>
                    </div>
                    <hr class="my-4">
                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="card">
                <div class="card-body">
                    <form action="/profile/edit" method="POST" enctype="multipart/form-data">
                        <#if user.accountType == "APPLICANT">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Surname</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="surname">
                                </div>
                            </div>
                        <#else>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Company Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                        </#if>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" name="password">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" name="phoneNumber">
                            </div>
                        </div>
                        <#if user.accountType == "APPLICANT">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="number" class="form-control" name="age">
                                </div>
                            </div>
                        <#else>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Company age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="number" class="form-control" name="age">
                                </div>
                            </div>
                        </#if>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Upload avatar</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="file" class="form-control" name="avatar">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
                                <button type="submit" class="btn btn-primary px-4">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>