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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

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
                                <a class="nav-link" href="/resumes/active"><@spring.message "layout.resumes"/></a>
                            </li>
                        </#if>
                    </#if>
                    <li class="nav-item">
                        <a class="nav-link" href="/"><@spring.message "layout.companies"/></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message "layout.change.lang"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="?lang=ru" onclick="changeLanguage('ru')"><@spring.message "layout.ru"/></a></li>
                            <li><a class="dropdown-item" href="?lang=en"  onclick="changeLanguage('en')"><@spring.message "layout.en"/></a></li>
                        </ul>
                    </li>
                </ul>
                <div class="ms-auto">
                    <#if known>
                        <a href="/profile" class="btn btn-outline-info m-2"><@spring.message "layout.profile"/></a>
                        <a href="/logout" class="btn btn-outline-info m-2" id="logout"><@spring.message "layout.logout"/></a>
                    <#else>
                        <a href="/auth/login" class="btn btn-outline-info m-2"><@spring.message "layout.login"/></a>
                        <a href="/auth/register" class="btn btn-outline-info m-2"><@spring.message "layout.register"/></a>
                    </#if>

                </div>
            </div>
        </div>
    </nav>
    <div class="container">
        <#nested spring>
    </div>
    </body>

    <script>
        function changeLanguage(lang) {
            let headers = new Headers;
            headers.set('Content-Type', 'application/json');
            let header = document.head.querySelector("[name~=_csrf_header][content]").content;
            let token = document.head.querySelector("[name~=_csrf_token][content]").content;

            headers.set(header, token);
            fetch('/profile/change_language', {
                method: 'POST',
                headers,
                body: new URLSearchParams({ lang: lang })
            }).then(() => {
                window.location.reload();
            });
        }
    </script>
    </html>

</#macro>
