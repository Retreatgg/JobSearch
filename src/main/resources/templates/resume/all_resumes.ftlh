<#import "../layot.ftlh" as main>
<#import "../paginatton.ftlh" as p>
<@main.layout script="">

    <h3 class="mb-4 mt-2">Resumes: ${page.totalElements}</h3>
    <div class="row mt-5">
        <div class="col-lg-10 mx-auto">
            <div class="career-search mb-60">

                <#list page.content as resume>
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
                                <a href="/resumes/${resume.id}" class="btn btn-light">More info</a>
                            </div>
                        </div>
                    </div>
                </#list>

            </div>
        </div>
    </div>

    <#assign maxPage = page.content?size / pageSize>
    <@p.pagination page=pageNumber maxPage=maxPage path="/resumes/active" category="" sort="">
    </@p.pagination>

</@main.layout>
