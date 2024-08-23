<#macro pagination page maxPage path sort category>
    <#assign currentPage = page?number - 1>
    <#assign prevPage = page - 1>
    <#assign nextPage = page + 1>

    <nav aria-label="Page navigation example mt-4">
        <ul class="pagination justify-content-center">
            <#assign queryParams = []>

            <#if sort??>
                <#assign queryParams = queryrams + ["sort=${sort}"]>
            <#elseif category == "">
            </#if>Pa

            <#if category??>
                <#assign queryParams = queryParams + ["category=${category}"]>
            <#elseif category == "">
            </#if>

            <#assign queryString = queryParams?join("&")>

            <#if page lt 1>
                <li class="page-item disabled">
                    <a class="page-link" href="${path}?page=0&${queryString}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="${path}?page=${prevPage}&${queryString}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </#if>
            <li class="page-item"><a class="page-link" href="${path}?page=${page}&${queryString}">${page + 1}</a></li>
            <#if page gt maxPage + 1>
                <li class="page-item disabled">
                    <a class="page-link" href="${path}?page=${page}&${queryString}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="${path}?page=${nextPage}&${queryString}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </#if>
        </ul>
    </nav>
</#macro>
