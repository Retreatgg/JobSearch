<#import "../layot.ftlh" as main>
<#import "../paginatton.ftlh" as p>

<@main.layout script="" ; spring>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <form action="/" method="get">
                    <div class="mb-3">
                        <label for="categoryFilter" class="form-label"><@spring.message "all.vacancies.select.category"/>:</label>
                        <select class="form-select" id="categoryFilter" name="category">
                            <option value=""><@spring.message "all.vacancies.categories"/></option>
                            <#list categories as category>
                                <option value="${category.name}">${category.name}</option>
                            </#list>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary"><@spring.message "all.vacancies.search"/></button>
                </form>
            </div>
            <div class="col-lg-6">
                <form action="/" method="get">
                    <div class="mb-3">
                        <label for="sortFilter" class="form-label"><@spring.message "all.vacancies.sort.by"/>:</label>
                        <select class="form-select" id="sortFilter" name="sort">
                            <option value=""><@spring.message "all.vacancies.sort.by"/></option>
                            <option value="updateTime"><@spring.message "all.vacancies.sort.publication.date"/></option>
                            <option value="salary"><@spring.message "all.vacancies.sort.salary"/></option>
                            <option value="countResponses"><@spring.message "all.vacancies.sort.responses"/></option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary"><@spring.message "all.vacancies.sort"/></button>
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
                                <span class="text-muted d-block"><@spring.message "all.vacancies.count.responses"/>: ${vacancy.countResponses}</span>
                            </div>
                            <div class="mt-3">
                                <a href="/vacancies/${vacancy.id}" class="btn btn-light"><@spring.message "all.vacancies.more.info"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <div class="row mt-4 justify-content-center">
            <div class="col-lg-6">
                <#assign maxPage = page.content?size / pageSize>
                <#if category??>
                    <@p.pagination page=pageNumber maxPage=maxPage path="/" category=category sort=""></@p.pagination>
                <#elseif sort??>
                    <@p.pagination page=pageNumber maxPage=maxPage path="/" category="" sort=sort></@p.pagination>
                <#elseif sort?? && category??>
                    <@p.pagination page=pageNumber maxPage=maxPage path="/" category=category sort=sort></@p.pagination>
                <#else>
                    <@p.pagination page=pageNumber maxPage=maxPage path="/" category="" sort=""></@p.pagination>
                </#if>

            </div>
        </div>
    </div>

</@main.layout>
