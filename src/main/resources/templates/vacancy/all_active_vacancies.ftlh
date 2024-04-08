<#import "../layot.ftlh" as main>
<#import "../paginatton.ftlh" as p>

<@main.layout>
    <h3 class="mb-4 mt-2">Vacancies: ${vacancies?size}</h3>

    <div class="row">
        <#list vacancies as vacancy>
            <div class="col-lg-4 col-md-6 col-12 mt-4">
                <div class="card border-0 bg-light rounded shadow">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <div class="mt-3">
                            <span class="text-muted d-block">$ ${vacancy.salary}</span>
                        </div>
                        <div class="job-right my-4 flex-shrink-0">
                            <a href="${vacancy.id}" class="btn btn-light">More info</a>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#assign maxPage = vacancies?size / perPage?number>
    <@p.pagination page maxPage>
    </@p.pagination>

</@main.layout>