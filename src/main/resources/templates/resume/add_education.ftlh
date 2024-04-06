<#import "../layot.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title text-center mb-4">Add Education Info</h5>
                        <form action="/resumes/add" method="post">
                            <div class="mb-3">
                                <label for="institution" class="form-label">Institution</label>
                                <input type="text" class="form-control" id="institution" name="institution">
                            </div>
                            <div class="mb-3">
                                <label for="program" class="form-label">Program</label>
                                <input type="text" class="form-control" id="program" name="program">
                            </div>
                            <div class="mb-3">
                                <label for="degree" class="form-label">Degree</label>
                                <input type="text" class="form-control" id="degree" name="degree">
                            </div>

                            <div class="mb-3">
                                <div id="datetimepicker1" class="input-append date">
                                    <label for="startDate" class="form-label">Start date</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div id="datetimepicker1" class="input-append date">
                                    <label for="endDate" class="form-label">Start date</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate"></input>
                                    <span class="add-on">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
        </span>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(function () {
                                    $('#datetimepicker1').datetimepicker({
                                        language: 'pt-BR'
                                    });
                                });
                            </script>


                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Add education info</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>