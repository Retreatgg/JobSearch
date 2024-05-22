<#import "../layot.ftlh" as main>

<@main.layout script="" ; spring>
    <div class="row justify-content-center mt-5">
        <div class="col-lg-6">
            <div class="card shadow mb-5">
                <div class="card-body text-center">
                    <h1 class="display-1">404</h1>
                    <h3 class="mb-4"><@spring.message "error.message"/></h3>
                    <p class="text-muted mb-4"><@spring.message "error.description"/>.</p>
                    <a href="/" class="btn btn-primary"><@spring.message "error.btn"/></a>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
