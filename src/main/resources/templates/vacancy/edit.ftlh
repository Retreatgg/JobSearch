<#import "../layot.ftlh" as main>
<@main.layout script="" ; spring>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4"><@spring.message "vacancy.edit.edit"/></h5>
                    <form action="/resumes/edit/${id}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label for="name" class="form-label"><@spring.message "vacancy.name"/></label>
                            <input type="text" class="form-control" id="name" value="${vacancy.name}" name="name">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label"><@spring.message "vacancy.description"/></label>
                            <input type="text" class="form-control" id="description" value="${vacancy.description}"
                                   name="description">
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label"><@spring.message "vacancy.salary"/></label>
                            <input type="number" class="form-control" id="salary" value="${vacancy.salary}"
                                   name="salary">
                        </div>

                        <div class="mb-3">
                            <label for="expFrom" class="form-label"><@spring.message "vacancy.exp.from"/></label>
                            <input type="number" class="form-control" id="expFrom" value="${vacancy.expFrom}"
                                   name="expFrom">
                        </div>

                        <div class="mb-3">
                            <label for="expTo" class="form-label"><@spring.message "vacancy.exp.to"/></label>
                            <input type="number" class="form-control" value="${vacancy.expTo}" id="expTo" name="expTo">
                        </div>

                        <div class="mb-3">
                            <label for="categoryId" class="form-label"><@spring.message "vacancy.categories"/></label>
                            <select class="form-select" aria-label="Default select example" id="categoryId"
                                    name="categoryId">
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <#if vacancy.isActive == true>
                                <div class="input-group-text">
                                    <input checked class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                                </div>
                                <input type="text" hidden="hidden" class="form-control" aria-label="Is active" name="isActive">
                            <#else>
                                <div class="input-group-text">
                                    <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                                </div>
                                <input type="text" hidden="hidden" class="form-control" aria-label="Is active" name="isActive">
                            </#if>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"><@spring.message "vacancy.edit.btn"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>