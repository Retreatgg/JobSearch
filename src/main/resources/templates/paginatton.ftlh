<#macro pagination page maxPage>
    <#assign page = page?number - 1>
    <#assign prevPage = page - 1>
    <#assign nextPage = page + 1>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <#if page == 0>
                <li class="page-item">
                    <a class="page-link" href="/vacancies/active?page=0" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="/vacancies/active?page=${prevPage}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </#if>
            <li class="page-item"><a class="page-link" href="/vacancies/active?page=${page}">${page + 1}</a></li>
            <#if page == maxPage>
                <li class="page-item">
                    <a class="page-link" href="/vacancies/active?page=${maxPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="/vacancies/active?page=${nextPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </#if>
        </ul>
    </nav>
</#macro>