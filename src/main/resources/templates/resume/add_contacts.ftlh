<#import "../layot.ftlh" as main>
    <#assign script><script src="../../static/resume_add.js"></script></#assign>
<@main.layout script=script>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">Add Contacts</h5>
                    <form action="/resumes/add" method="post" id="cont">
                        <div class="mb-3">
                            <label for="accountType" class="form-label">Select contact</label>
                            <select class="form-select" aria-label="Default select example" name="typeId">
                                <#list contacts as contact>
                                    <option value="${contact.id}">${contact.type}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="value" class="form-label">Value</label>
                            <input type="text" class="form-control" id="value" name="value">
                        </div>

                        <div class="text-center">
                            <button type="button" class="btn btn-primary"><a href="resumes/add">Add contacts</a></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>