<#import "../layot.ftlh" as main>
<@main.layout script="">
    <style>
        body {
            background-color: #f4f7fa;
            font-family: 'Arial', sans-serif;
        }

        .container-fluid {
            height: 100vh;
        }

        .h-custom {
            height: calc(100% - 140px);
        }

        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-outline {
            position: relative;
            margin-bottom: 1.5rem;
        }

        .form-outline input {
            padding: 1rem;
            border-radius: 1.5rem;
        }

        .form-outline label {
            position: absolute;
            top: 1rem;
            left: 1rem;
            color: #6c757d;
            transition: all 0.3s;
            pointer-events: none;
        }

        .form-outline input:focus ~ label,
        .form-outline input:not(:placeholder-shown) ~ label {
            top: 0.25rem;
            left: 1rem;
            font-size: 0.75rem;
            color: #007bff;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            border-radius: 2.5rem;
            padding: 0.75rem 2.5rem;
            transition: all 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .link-danger {
            color: #dc3545;
        }

        .link-danger:hover {
            text-decoration: none;
            color: #bb2124;
        }
    </style>

    <section class="vh-100 mt-5">
        <div class="container-fluid h-custom mt-5">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                         class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <form class="mt-5" id="login-form" action="/auth/login" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>

                        <div class="form-outline mb-4">
                            <input type="email" id="form3Example3" class="form-control form-control-lg"
                                   placeholder="Enter a valid email address" name="username"/>
                            <label class="form-label" for="form3Example3"></label>
                        </div>

                        <div class="form-outline mb-3">
                            <input type="password" id="form3Example4" class="form-control form-control-lg"
                                   placeholder="Enter password" name="password" autocomplete="on"/>
                            <label class="form-label" for="form3Example4"></label>
                        </div>

                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button id="btn" type="submit" class="btn btn-primary btn-lg"
                                    style="padding-left: 2.5rem; padding-right: 2.5rem;">Login
                            </button>
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="/auth/choose"
                                                                                              class="link-danger">Register</a>
                            </p>
                            <p class="small fw-bold mt-2 pt-1 mb-0"><a href="/auth/forgot_password">Forgot
                                    password?</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</@main.layout>
