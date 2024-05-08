<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title text-center mb-4">Registration Form</h5>
                        <form action="/users/register" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            </#if>
                            <div class="mb-3">
                                <label for="name" class="form-label">Your Name</label>
                                <input type="text" class="form-control" id="name" name="name">
                            </div>
                            <div class="mb-3">
                                <label for="surname" class="form-label">Your Surname</label>
                                <input type="text" class="form-control" id="surname" name="surname">
                            </div>
                            <div class="mb-3">
                                <label for="age" class="form-label">Your Age</label>
                                <input type="number" class="form-control" id="age" name="age">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Your Email</label>
                                <input type="email" class="form-control" id="email" name="email">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Your Password</label>
                                <input type="password" class="form-control" id="password" name="password">
                            </div>
                            <div class="mb-3">
                                <label for="phoneNumber" class="form-label">Your Phone Number</label>
                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                            </div>
                            <div class="mb-3">
                                <label for="accountType" class="form-label">Select Account Type</label>
                                <select class="form-select" aria-label="Default select example" name="accountType">
                                    <option value="APPLICANT">APPLICANT</option>
                                    <option value="EMPLOYER">EMPLOYER</option>
                                </select>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Registration</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
