<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="main-body mt-5">

        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <#if messages??>
                            <div class="card-body">
                                <button onclick="callResponses();" type="button" class="btn btn-danger btn-block mb-3">
                                    <div class="position-relative">
                                        <div class="position-absolute top-0 start-100 translate-middle bg-danger badge rounded-pill">
                                            <span>${messages?size}</span>
                                        </div>
                                        Responses
                                    </div>
                                </button>
                            </div>

                            <div class="modal shadow-lg" id="modal">
                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                    <div class="modal-content bg-light border-danger">
                                        <div class="modal-header bg-danger text-white">
                                            <h5 class="modal-title">Responses</h5>
                                            <button type="button" class="btn-close" onclick="closeModal();"
                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div id="body-modal" class="modal-body">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="/images/download/${user.email}" alt="User Avatar"
                                 class="rounded-circle border border-primary" width="150">
                            <div class="mt-3">
                                <h4>${user.name}</h4>
                                <div class="row">
                                    <#if user.accountType == "EMPLOYER">
                                        <div class="col-sm-6">
                                            <a class="btn btn-info btn-block" target="__blank"
                                               href="/vacancies/add">ADD VACANCY</a>
                                        </div>
                                    <#else>
                                        <div class="col-sm-6">
                                            <a class="btn btn-info btn-block" target="__blank"
                                               href="/resumes/add">ADD RESUME</a>
                                        </div>
                                    </#if>

                                    <div class="col-sm-6">
                                        <a class="btn btn-info btn-block" target="__blank"
                                           href="/profile/edit/${user.email}">EDIT PROFILE</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <#if user.accountType == "Applicant">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Full Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.name} ${user.surname}
                                </div>
                            </div>
                        <#else>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Company name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.name}
                                </div>
                            </div>
                        </#if>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.phoneNumber}
                            </div>
                        </div>
                        <#if user.accountType == "Applicant">
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.age}
                                </div>
                            </div>
                        <#else>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Company age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.age}
                                </div>
                            </div>
                        </#if>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Account type</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.accountType}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <#if user.accountType == "EMPLOYER">
            <#if vacancies??>
                <h3 class="mt-5">My vacancies</h3>
                <div class="row">
                    <#list vacancies as vacancy>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow">
                                <div class="card-body p-4">
                                    <h5>${vacancy.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block">${vacancy.description}</span>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="col">
                                            <button data-target="${vacancy.id}" class="btn btn-info btn-block btn-update-vacancies">Update</button>
                                        </div>
                                        <div class="col">
                                            <a href="/vacancies/edit/${vacancy.id}" target="__blank"
                                               class="btn btn-info btn-block">Edit</a>
                                        </div>
                                        <div class="col">
                                            <a href="/vacancies/${vacancy.id}" target="__blank"
                                               class="btn btn-info btn-block">Details</a>
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
                <div class="row">
                    <h3 class="mt-5">My resumes</h3>
                    <#list resumes as resume>
                        <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                            <div class="card border-0 bg-light rounded shadow">
                                <div class="card-body p-4">
                                    <h5>${resume.name}</h5>
                                    <div class="mt-3">
                                        <span class="text-muted d-block"></span>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="col">
                                            <button data-target="${resume.id}" class="btn btn-info btn-block btn-update-resumes">Update</button>
                                        </div>
                                        <div class="col">
                                            <a href="/resumes/edit/${resume.id}" target="__blank"
                                               class="btn btn-info btn-block">Edit</a>
                                        </div>
                                        <div class="col">
                                            <a href="/resumes/${resume.id}" target="__blank"
                                               class="btn btn-info btn-block">Details</a>
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


    <script>
        const btnUpdateVacancies = document.querySelectorAll('.btn-update-vacancies');

        let headers = new Headers()

        let header = document.head.querySelector("[name~=_csrf_header][content]").content;
        let token = document.head.querySelector("[name~=_csrf_token][content]").content;

        headers.set(header, token)

        btnUpdateVacancies.forEach(btn => {
            btn.addEventListener('click', () => {
                const vacancyId = btn.getAttribute('data-target');
                console.log(vacancyId)
                fetch("/vacancies/update/" + vacancyId, {
                    method: 'POST',
                    headers
                })
            });
        });

        const btnUpdateResumes = document.querySelectorAll('.btn-update-resumes');

        btnUpdateResumes.forEach(btn => {
            btn.addEventListener('click', () => {
                const resumeId = btn.getAttribute('data-target');
                console.log(resumeId)
                fetch("/resumes/update/" + resumeId, {
                    method: 'POST',
                    headers
                })
            });
        });


        const modal = document.getElementById('modal');
        const close = document.getElementById('close');
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
            cards.appendChild(h3)
            cards.innerHTML =
                `
                <#if activeResumesWithResponses??>
                    <#list activeResumesWithResponses as resume>
                <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                    <div class="card border-0 bg-light rounded shadow">
                        <div class="card-body p-4">
                            <h5>${resume.name}</h5>
                            <div class="mt-3">
                                <span class="text-muted d-block"></span>
                            </div>

                            <div class="row mt-3">
                                <div class="col">
                                    <a href="/chat/open?toUser=${resume.employerId}" target="__blank" class="btn btn-info btn-block">To chat</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#list>
                </#if>
            </div>
                `
            body.appendChild(cards);
        }

        function closeModal(cards) {
            modal.classList.remove('show')
            modal.style.display = 'none'
            body.removeChild(cards);
        }

    </script>
</@main.layout>
