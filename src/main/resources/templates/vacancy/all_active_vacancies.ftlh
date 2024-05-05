<#import "../layot.ftlh" as main>
<#import "../paginatton.ftlh" as p>

<@main.layout script="">


    <div class="form-group">
        <label for="categoryFilter">Select category:</label>
        <form action="/" method="get">
            <select class="form-control" id="categoryFilter" name="category">
                <option value="">Categories</option>
                <#list categories as category>
                    <option value="${category.name}">${category.name}</option>
                </#list>
            </select>
            <button type="submit" class="btn btn-primary mt-3">Search</button>
        </form>
    </div>

    <div class="row">
        <#list page.content as vacancy>
            <div class="col-lg-4 col-md-6 col-12 mt-4">
                <div class="card border-0 bg-light rounded shadow">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <div class="mt-3">
                            <span class="text-muted d-block">$ ${vacancy.salary}</span>
                        </div>
                        <div class="job-right my-4 flex-shrink-0">
                            <a href="/vacancies/${vacancy.id}" class="btn btn-light">More info</a>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#assign maxPage = page.content?size / 1>
    <@p.pagination page=page maxPage=maxPage path="/">
    </@p.pagination>

</@main.layout>