<#macro layout script>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Title</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">

        ${script}
        <style>
            body {
                background-color: #f8f9fa;
            }

            .navbar {
                background-color: #ffffff;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }

            .navbar-brand {
                color: #343a40;
            }

            .nav-link {
                color: #6c757d;
            }

            .nav-link:hover {
                color: #495057;
            }

            .btn-outline-success {
                color: #28a745;
                border-color: #28a745;
            }

            .btn-outline-success:hover {
                color: #ffffff;
                background-color: #28a745;
                border-color: #28a745;
            }
        </style>
    </head>
    <body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="/vacancies/active">Job Search</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-fill">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/vacancies/active">For applicants</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/resumes/active">For employers</a>
                        </li>
                        <li class="nav-item ms-auto">
                            <a class="nav-link active" aria-current="page" href="/login">Log In</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
        <#nested>
    </div>
    </body>
    </html>
</#macro>
