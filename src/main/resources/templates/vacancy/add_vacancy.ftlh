<#import "../layot.ftlh" as main>
<#assign script>
    <script src="../../static/vacancy_add.js"></script>
</#assign>

<@main.layout script=script ; spring>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4"><@spring.message "vacancy.add.add"/></h5>
                    <form id="form" action="/vacancies/add" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <div class="mb-3">
                            <label for="name" class="form-label"><@spring.message "vacancy.name"/></label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label"><@spring.message "vacancy.description"/></label>
                            <input type="text" class="form-control" id="description" name="description">
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label"><@spring.message "vacancy.salary"/></label>
                            <input type="number" class="form-control" id="salary" name="salary">
                        </div>
                        <div class="mb-3">
                            <label for="expFrom" class="form-label"><@spring.message "vacancy.exp.from"/></label>
                            <input type="number" class="form-control" id="expFrom" name="expFrom">
                        </div>
                        <div class="mb-3">
                            <label for="expTo" class="form-label"><@spring.message "vacancy.exp.to"/></label>
                            <input type="number" class="form-control" id="expTo" name="expTo">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label"><@spring.message "vacancy.categories"/></label>
                            <select class="form-select" aria-label="Default select example" id="categoryName" name="categoryName">
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="checkbox" value="true"
                                       aria-label="Checkbox for following text input" name="isActive">
                                <span><@spring.message "vacancy.add.is.active"/></span>
                            </div>
                            <input type="text" hidden="hidden" class="form-control" aria-label="Is active" name="isActive">
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"><@spring.message "vacancy.add.btn"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>

