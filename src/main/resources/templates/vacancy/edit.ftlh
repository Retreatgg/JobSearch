<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">Add vacancy</h5>
                    <form action="/resumes/edit/{id}" method="post">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control" id="description" name="description">
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="number" class="form-control" id="salary" name="salary">
                        </div>

                        <div class="mb-3">
                            <label for="expFrom" class="form-label">Experience from</label>
                            <input type="number" class="form-control" id="expFrom" name="expFrom">
                        </div>

                        <div class="mb-3">
                            <label for="expTo" class="form-label">Experience to</label>
                            <input type="number" class="form-control" id="expTo" name="expTo">
                        </div>

                        <div class="mb-3">
                            <label for="categoryId" class="form-label">Categories</label>
                            <select class="form-select" aria-label="Default select example" id="categoryId" name="categoryId">
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                            </div>
                            <input type="text" hidden="hidden" class="form-control" aria-label="Is active" name="isActive">
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Edit vacancy</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>