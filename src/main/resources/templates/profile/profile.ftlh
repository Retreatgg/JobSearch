<#import "../layot.ftlh" as main>
<@main.layout script="" ; spring>

    <style>
        body {
            background-color: #f4f7fa;
            font-family: 'Arial', sans-serif;
        }

        .main-body {
            margin-top: 5rem;
        }

        .profile-img {
            border-radius: 50%;
            border: 5px solid #007bff;
        }

        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-body h6 {
            color: #6c757d;
            font-weight: bold;
        }

        .btn-custom {
            background-color: #007bff;
            color: #fff;
            border-radius: 50px;
            padding: 10px 20px;
            transition: all 0.3s;
        }

        .btn-custom:hover {
            background-color: #0056b3;
            color: #fff;
        }

        .badge {
            position: absolute;
            top: 0;
            right: 0;
        }

        .modal-header {
            background-color: #007bff;
        }

        .modal-header .close {
            color: #fff;
        }

        .modal-content {
            border-radius: 1rem;
        }

        .modal-title {
            font-weight: bold;
        }

        .rounded-btn {
            border-radius: 50px;
        }

        .info-text {
            color: #495057;
        }

        .vacancies-resumes h5 {
            color: #007bff;
        }

        .vacancies-resumes .text-muted {
            font-size: 0.875rem;
        }

        .shadow-lg {
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
        }
    </style>

    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body text-center">
                        <#if messages??>
                            <div class="position-relative">
                                <button onclick="callResponses();" type="button"
                                        class="btn btn-custom btn-block mb-3 rounded-btn position-relative">
                                     <@spring.message "profile.responses"/>
                                    <span class="badge badge-danger badge-pill">${messages?size}</span>
                                </button>
                            </div>
                        </#if>
                        <img src="/images/download/${user.email}" alt="User Avatar" class="profile-img mb-3"
                             width="150">
                        <h4>${user.name}</h4>
                        <div class="row mt-3">
                            <#if user.accountType == "EMPLOYER">
                                <div class="col-sm-6">
                                    <a class="btn btn-custom btn-block rounded-btn" target="__blank"
                                       href="/vacancies/add"><@spring.message "profile.add.vacancies"/></a>
                                </div>
                            <#else>
                                <div class="col-sm-6">
                                    <a class="btn btn-custom btn-block rounded-btn" target="__blank"
                                       href="/resumes/add"><@spring.message "profile.add.resumes"/></a>
                                </div>
                            </#if>
                            <div class="col-sm-6">
                                <a class="btn btn-custom btn-block rounded-btn" target="__blank"
                                   href="/profile/edit"><@spring.message "profile.edit"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <#if user.accountType == "APPLICANT">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><@spring.message "profile.full.name"/></h6>
                                </div>
                                <div class="col-sm-9 info-text">
                                    ${user.name} ${user.surname}
                                </div>
                            </div>
                        <#else>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><@spring.message "profile.company.name"/></h6>
                                </div>
                                <div class="col-sm-9 info-text">
                                    ${user.name}
                                </div>
                            </div>
                        </#if>
                        <hr>
                        <#if user.accountType == "APPLICANT">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><@spring.message "profile.age.employer"/></h6>
                                </div>
                                <div class="col-sm-9 info-text">
                                    ${user.age}
                                </div>
                            </div>
                        <#else>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><@spring.message "profile.age.company"/></h6>
                                </div>
                                <div class="col-sm-9 info-text">
                                    ${user.age}
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>

        <#if user.accountType == "EMPLOYER">
            <#if vacancies??>
                <h3 class="mt-5"><@spring.message "profile.my.vacancies"/></h3>
                <div class="row vacancies-resumes">
                    <#list vacancies as vacancy>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow-lg">
                                <div class="card-body p-4">
                                    <h5>${vacancy.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block">${vacancy.description}</span>
                                        <span class="mt-2 text-muted d-block"><strong><@spring.message "profile.created.date"/></strong>: ${vacancy.createdDate}</span>
                                        <span class="mt-2 text-muted d-block"><strong><@spring.message "profile.update.date"/></strong>: ${vacancy.updateDate}</span>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col">
                                            <button data-target="${vacancy.id}"
                                                    class="btn btn-custom btn-block rounded-btn btn-update-vacancies">
                                                <@spring.message "profile.update.vacancy"/>
                                            </button>
                                        </div>
                                        <div class="col">
                                            <a href="/vacancies/edit/${vacancy.id}" target="__blank"
                                               class="btn btn-custom btn-block rounded-btn"><@spring.message "profile.edit.vacancy"/></a>
                                        </div>
                                        <div class="col">
                                            <a href="/vacancies/${vacancy.id}" target="__blank"
                                               class="btn btn-custom btn-block rounded-btn"><@spring.message "profile.vacancy.details"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
        </#if>
        <#if user.accountType == "APPLICANT">
            <#if resumes??>
                <h3 class="mt-5"><@spring.message "profile.my.resumes"/></h3>
                <div class="row vacancies-resumes">
                    <#list resumes as resume>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow-lg">
                                <div class="card-body p-4">
                                    <h5>${resume.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block"></span>
                                        <span class="mt-2 text-muted d-block"><strong><@spring.message "profile.created.date"/></strong>: ${resume.createdDate}</span>
                                        <span class="mt-2 text-muted d-block"><strong><@spring.message "profile.update.date"/></strong>: ${resume.updateTime}</span>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col">
                                            <button data-target="${resume.id}"
                                                    class="btn btn-custom btn-block rounded-btn btn-update-resumes">
                                                <@spring.message "profile.update.resume"/>
                                            </button>
                                        </div>
                                        <div class="col">
                                            <a href="/resumes/edit/${resume.id}" target="__blank"
                                               class="btn btn-custom btn-block rounded-btn"><@spring.message "profile.edit.resume"/></a>
                                        </div>
                                        <div class="col">
                                            <a href="/resumes/${resume.id}" target="__blank"
                                               class="btn btn-custom btn-block rounded-btn"><@spring.message "profile.resume.details"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
        </#if>
    </div>

    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-white" id="modalTitle"><@spring.message "profile.responses"/></h5>
                    <button type="button" onclick="closeModal()" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="body-modal" class="modal-body">
                </div>
            </div>
        </div>
    </div>

    <script>
        const btnUpdateVacancies = document.querySelectorAll('.btn-update-vacancies');

        let headers = new Headers()

        let header = document.head.querySelector("[name~=_csrf_header][content]").content;
        let token = document.head.querySelector("[name~=_csrf_token][content]").content;

        headers.set(header, token)

        btnUpdateVacancies.forEach(btn => {
            btn.addEventListener('click', () => {
                const vacancyId = btn.getAttribute('data-target');
                fetch("/vacancies/update/" + vacancyId, {
                    method: 'POST',
                    headers
                });
            });
        });

        const btnUpdateResumes = document.querySelectorAll('.btn-update-resumes');

        btnUpdateResumes.forEach(btn => {
            btn.addEventListener('click', () => {
                const resumeId = btn.getAttribute('data-target');
                fetch("/resumes/update/" + resumeId, {
                    method: 'POST',
                    headers
                });
            });
        });

        const modal = document.getElementById('modal');
        const body = document.getElementById('body-modal');
        let cards;

        function callResponses() {
            modal.classList.add('show');
            modal.style.display = 'block';
            if (cards) {
                body.removeChild(cards);
            }
            makeCard();
        }

        function makeCard() {
            cards = document.createElement('div');
            cards.className = 'row';

            let h3 = document.createElement('h3');
            h3.innerText = 'My resumes with responses';
            cards.appendChild(h3);
            cards.innerHTML = `
                <#if activeResumesWithResponses??>
                    <#list activeResumesWithResponses as resume>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow-lg">
                                <div class="card-body p-4">
                                    <h5>${resume.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block"></span>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col">
                                            <a href="/chat/open?toUser=${resume.employerId}" target="__blank" class="btn btn-custom btn-block rounded-btn"><@spring.message "profile.to.chat"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            `;
            body.appendChild(cards);
        }

        function closeModal() {
            modal.classList.remove('show');
            modal.style.display = 'none';
            if (cards) {
                body.removeChild(cards);
            }
        }
    </script>

</@main.layout>
