<#import "../layot.ftlh" as main>
<@main.layout script="" ; spring>
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
        </div>

        <#if work_info??>
           <#if work_info?size gt 0>
               <div class="job-box">
                   <h6 class="text-center text-md-left"><@spring.message "resume.work.experience"/>:</h6>
                   <ul class="list-unstyled">
                       <#list work_info as work>
                           <li>
                               <hr>
                               <i class="zmdi zmdi-money mr-2"><span
                                           class="font-weight-bold"><@spring.message "resume.company"/>:: ${work.companyName}<br></span></i>
                               <i class="zmdi zmdi-money mr-2"><span
                                           class="font-weight-bold"><@spring.message "resume.position"/>:: ${work.position}<br> </span></i>
                               <i class="zmdi zmdi-money mr-2"><span
                                           class="font-weight-bold"><@spring.message "resume.years"/>:: ${work.years}<br> </span></i>
                               <i class="zmdi zmdi-money mr-2"><span
                                           class="font-weight-bold"><@spring.message "resume.responsibilities"/>:: ${work.responsibilities}</span></i>
                           </li>
                       </#list>
                   </ul>
               </div>
           </#if>
        </#if>

        <#if educations??>
            <#if educations?size gt 0>
                <div class="job-box mt-5">
                    <h6 class="text-center text-md-left"><@spring.message "resume.educations"/>::</h6>
                    <ul class="list-unstyled">
                        <#list educations as education>
                            <li>
                                <hr>
                                <i class="zmdi zmdi-money mr-2"><span
                                            class="font-weight-bold"><@spring.message "resume.institute"/>:: ${education.institution}<br></span></i>
                                <i class="zmdi zmdi-money mr-2"><span
                                            class="font-weight-bold"><@spring.message "resume.program"/>:: ${education.program}<br> </span></i>
                                <i class="zmdi zmdi-money mr-2"><span
                                            class="font-weight-bold"><@spring.message "resume.degree"/>:: ${education.degree}<br> </span></i>
                                <i class="zmdi zmdi-money mr-2"><span
                                            class="font-weight-bold"><@spring.message "resume.start.date"/>:: ${education.startDate}<br></span></i>
                                <i class="zmdi zmdi-money mr-2"><span
                                            class="font-weight-bold"><@spring.message "resume.end.date"/>:: ${education.endDate} </span></i>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>
        </#if>
    </div>
</@main.layout>
