<#import "../layot.ftlh" as main>
<@main.layout script="" ; spring>
    <div class="row mt-5">
    <div class="col-lg-10 mx-auto">
        <div class="career-search mb-60">

            <#if resumes??>
                <#if resumes?size == 0>
                    <p><@spring.message "profile.responses.text"/></p>
                <#else>
                    <#list resumes as resume>
                        <div class="filter-result" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; padding: 20px; border-radius: 10px; margin-bottom: 20px;">

                            <div class="job-box d-md-flex align-items-center justify-content-between mb-30">
                                <div class="job-left my-4 d-md-flex align-items-center flex-wrap">

                                    <div class="job-content">
                                        <h5 class="text-center text-md-left">${resume.name}</h5>
                                        <ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
                                            <li class="mr-md-4">
                                                <i class="zmdi zmdi-money mr-2"></i> ${resume.salary}
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="job-right my-4 flex-shrink-0">
                                    <a href="/chat/open?toUser=${resume.applicant}" class="btn btn-light"><@spring.message "profile.responses.chat"/></a>
                                </div>
                            </div>
                        </div>
                    </#list>

                </#if>
            </#if>

            <#if vacancies??>
                <#if vacancies?size == 0>
                    <p><@spring.message "profile.responses.text.resume"/></p>
                <#else>
                    <#list vacancies as vacancy>
                        <div class="filter-result" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); background-color: #fff; padding: 20px; border-radius: 10px; margin-bottom: 20px;">

                            <div class="job-box d-md-flex align-items-center justify-content-between mb-30">
                                <div class="job-left my-4 d-md-flex align-items-center flex-wrap">

                                    <div class="job-content">
                                        <h5 class="text-center text-md-left">${vacancy.name}</h5>
                                        <ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
                                            <li class="mr-md-4">
                                                <i class="zmdi zmdi-money mr-2"></i> ${vacancy.salary}
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="job-right my-4 flex-shrink-0">
                                    <a href="/chat/open?toUser=${vacancy.author.id}" class="btn btn-light"><@spring.message "profile.responses.chat"/></a>
                                </div>
                            </div>
                        </div>
                    </#list>

                </#if>
            </#if>
        </div>
    </div>
</@main.layout>