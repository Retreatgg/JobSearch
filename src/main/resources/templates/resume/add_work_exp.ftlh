<#import "../layot.ftlh" as main>
<#assign script><script src="../../static/resume_add.js"></script></#assign>

<@main.layout script=script>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title text-center mb-4">Add Work Experience</h5>
                        <form action="/resumes/add" method="post" id="exp">
                            <div class="mb-3">
                                <label for="years" class="form-label">Years</label>
                                <input type="number" class="form-control" id="years" name="years">
                            </div>
                            <div class="mb-3">
                                <label for="companyName" class="form-label">Company name</label>
                                <input type="text" class="form-control" id="companyName" name="companyName">
                            </div>
                            <div class="mb-3">
                                <label for="position" class="form-label">Position</label>
                                <input type="text" class="form-control" id="position" name="position">
                            </div>

                            <div class="mb-3">
                                <label for="responsibilities" class="form-label">Responsibilities</label>
                                <input type="text" class="form-control" id="responsibilities" name="responsibilities">
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Add work experience info</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>