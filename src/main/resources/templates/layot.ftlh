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
        <style>
            body {
                background-color: #f8f9fa;
            }

            .navbar {
                border-bottom: 1px solid rgba(0, 0, 0, 0.1);
            }

            .navbar-brand {
                font-weight: bold;
                color: #007bff;
            }

            .navbar-nav .nav-link {
                color: #007bff;
                font-weight: 500;
            }

            .navbar-nav .nav-link:hover {
                color: #0056b3;
            }

            .btn-outline-info {
                color: #007bff;
                border-color: #007bff;
                font-weight: 500;
            }

            .btn-outline-info:hover {
                color: #0056b3;
                background-color: transparent;
                border-color: #0056b3;
            }
        </style>
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
                    <#assign known = SPRING_SECURITY_CONTEXT??>
                    <#if known>
                        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#assign role = user.getAuthorities()[0]>
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
                        <a href="/auth/choose" class="btn btn-outline-info m-2">Register</a>
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
