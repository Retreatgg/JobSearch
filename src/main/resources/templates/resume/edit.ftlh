<#import "../layot.ftlh" as main>
<@main.layout script="" ; spring>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4"><@spring.message "resume.edit.resume"/></h5>
                    <form action="/resumes/edit/${id}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label for="title" class="form-label"><@spring.message "resume.edit.title"/></label>
                            <input type="text" class="form-control" id="title" value="${resume.name}" name="title">
                        </div>

                        <div class="mb-3">
                            <label for="categoryName"
                                   class="form-label"><@spring.message "resume.edit.select.category"/></label>
                            <select class="form-select" aria-label="Default select example" name="categoryName">
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="salary" class="form-label"><@spring.message "resume.edit.salary"/></label>
                            <input type="number" class="form-control" id="salary" value="${resume.salary}"
                                   name="salary">
                        </div>

                        <div class="input-group mb-3">
                            <#if resume.isActive == true>
                                <div class="input-group-text">
                                    <input class="form-check-input mt-0" checked type="checkbox" value=""
                                           aria-label="Checkbox for following text input">
                                </div>
                                <input type="text" class="form-control" aria-label="Is active" name="isActive">
                            <#else>
                                <div class="input-group-text">
                                    <input class="form-check-input mt-0" type="checkbox" value=""
                                           aria-label="Checkbox for following text input">
                                </div>
                                <input type="text" class="form-control" aria-label="Is active" name="isActive">
                            </#if>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"><@spring.message "resume.edit.btn"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
