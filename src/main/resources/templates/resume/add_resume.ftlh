<#import "../layot.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">Add resume</h5>
                    <form action="/resumes/add" method="post">
                        <div class="mb-3">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                        <div class="mb-3">
                            <label for="category" class="form-label">Category</label>
                            <input type="text" class="form-control" id="category" name="category">
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="number" class="form-control" id="salary" name="salary">
                        </div>

                        <div class="col-sm-6">
                            <a class="btn btn-info btn-block" target="__blank"
                               href="/resumes/add/work_info">ADD WORK EXPERIENCE INFO</a>
                        </div>

                        <div class="col-sm-6">
                            <a class="btn btn-info btn-block" target="__blank"
                               href="/resumes/add/education">ADD EDUCATION INFO</a>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="checkbox" value=""
                                       aria-label="Checkbox for following text input">
                            </div>
                            <input type="text" class="form-control" aria-label="Is active" name="isActive">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Add resume</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>