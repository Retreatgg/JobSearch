<#macro pagination page maxPage path>
    <#assign currentPage = page?number - 1>
    <#assign prevPage = page - 1>
    <#assign nextPage = page + 1>

    <nav aria-label="Page navigation example mt-4">
        <ul class="pagination justify-content-center">
            <#if page lt 1>
                <li class="page-item disabled">
                    <a class="page-link" href="${path}?page=0" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="${path}?page=${prevPage}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </#if>
            <li class="page-item"><a class="page-link" href="${path}?page=${page}">${page + 1}</a></li>
            <#if page gt maxPage + 1>
                <li class="page-item disabled">
                    <a class="page-link"
                       href="${path}?page=${page}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="${path}?page=${nextPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </#if>
        </ul>
    </nav>
</#macro>
