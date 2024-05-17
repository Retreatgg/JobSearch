<#import "../layot.ftlh" as main>
<#import "../paginatton.ftlh" as p>

<@main.layout script="">

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <form action="/" method="get">
                    <div class="mb-3">
                        <label for="categoryFilter" class="form-label">Select category:</label>
                        <select class="form-select" id="categoryFilter" name="category">
                            <option value="">Categories</option>
                            <#list categories as category>
                                <option value="${category.name}">${category.name}</option>
                            </#list>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
            <div class="col-lg-6">
                <form action="/" method="get">
                    <div class="mb-3">
                        <label for="sortFilter" class="form-label">Sort by:</label>
                        <select class="form-select" id="sortFilter" name="sort">
                            <option value="">Sort by</option>
                            <option value="updateTime">Publication date</option>
                            <option value="countResponses">Count responses</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Sort</button>
                </form>
            </div>
        </div>

        <div class="row mt-4">
            <#list page.content as vacancy>
                <div class="col-lg-4 col-md-6 col-12 mt-4">
                    <div class="card border-0 bg-light rounded shadow">
                        <div class="card-body">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <div class="mt-3">
                                <span class="text-muted d-block">$ ${vacancy.salary}</span>
                            </div>
                            <div class="mt-3">
                                <span class="text-muted d-block">Count responses: ${vacancy.countResponses}</span>
                            </div>
                            <div class="mt-3">
                                <a href="/vacancies/${vacancy.id}" class="btn btn-light">More info</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <div class="row mt-4 justify-content-center">
            <div class="col-lg-6">
                <#assign maxPage = page.content?size / pageSize>
                <@p.pagination page=pageNumber maxPage=maxPage path="/">
                </@p.pagination>
            </div>
        </div>
    </div>

</@main.layout>
