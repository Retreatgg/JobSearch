<#import "spring.ftl" as spring>
<#macro layout script="">

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <meta name="_csrf_token" content="${_csrf.token}">
        <title>Job Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
                crossorigin="anonymous"></script>

    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="/">Job Search</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <#assign
                known = SPRING_SECURITY_CONTEXT??
                >
                <#if known>
                    <#assign
                    user = SPRING_SECURITY_CONTEXT.authentication.principal
                    role = user.getAuthorities()[0]
                    >
                    <#if role == "EMPLOYER">
                            <li class="nav-item">
                                <a class="nav-link" href="/resumes/active">Employers</a>
                            </li>
                    </#if>

                </#if>

                    <li class="nav-item">
                        <a class="nav-link" href="/">Companies</a>
                    </li>
                </ul>

                <div class="ms-auto">
                    <#if known>
                        <a href="/profile" class="btn btn-outline-info m-2">Profile</a>
                        <a href="/logout" class="btn btn-outline-info m-2" id="logout">Logout</a>
                    <#else>
                        <a href="/auth/login" class="btn btn-outline-info m-2">Login</a>
                        <a href="/auth/register" class="btn btn-outline-info m-2">Register</a>
                    </#if>
                </div>
            </div>

        </div>
    </nav>

    <div class="container">
        <#nested spring>
    </div>

    </body>
    </html>

</#macro>