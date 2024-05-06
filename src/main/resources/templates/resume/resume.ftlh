<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="filter-result"
         style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; padding: 20px; border-radius: 10px; margin-bottom: 20px;">

        <div class="job-box d-md-flex align-items-center justify-content-between mb-30">
            <div class="job-left my-4 d-md-flex align-items-center flex-wrap">

                <div class="job-content">
                    <h5 class="text-center text-md-left">${resume.name}</h5>
                    <ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
                        <li class="mr-md-4">
                            <i class="zmdi zmdi-money mr-2"></i> $ ${resume.salary}
                        </li>
                    </ul>
                </div>
            </div>
            <div class="job-right my-4 flex-shrink-0">
                <a href="#" class="btn btn-light">Respond</a>
            </div>
        </div>

        <#if work_info?size != 0>
            <div class="job-box">
                <h6 class="text-center text-md-left">Work Experience:</h6>
                <ul class="list-unstyled">
                    <#list work_info as work>
                        <li>
                            <hr>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Company: ${work.companyName}<br></span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Position: ${work.position}<br> </span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Years: ${work.years}<br> </span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Responsibilities: ${work.responsibilities}</span></i>
                        </li>
                    </#list>
                </ul>
            </div>
        </#if>

        <#if educations?size != 0>
            <div class="job-box mt-5">
                <h6 class="text-center text-md-left">My educations:</h6>
                <ul class="list-unstyled">
                    <#list educations as education>
                        <li>
                            <hr>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Institution: ${education.institution}<br></span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Program: ${education.program}<br> </span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Degree: ${education.degree}<br> </span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">Start date: ${education.startDate}<br></span></i>
                            <i class="zmdi zmdi-money mr-2"><span
                                        class="font-weight-bold">End date: ${education.endDate} </span></i>
                        </li>
                    </#list>
                </ul>
            </div>
        </#if>
    </div>
</@main.layout>
