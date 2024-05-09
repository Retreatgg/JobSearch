<#import "../layot.ftlh" as main>

<@main.layout script="">
    <div class="row justify-content-center mt-5">
        <div class="col-lg-8">
            <div class="card shadow mb-5">
                <div class="card-body">

                    <h5 class="card-title">${vacancy.name}</h5>
                    <p class="card-text">${vacancy.description}</p>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><strong>Salary:</strong> $${vacancy.salary} per year</li>
                        <li class="list-group-item"><strong>Experience:</strong> ${vacancy.expFrom} - ${vacancy.expTo}
                            years
                        </li>
                    </ul>


                </div>
                <#if auth??>
                    <div class="job-right my-4 flex-shrink-0">
                        <button class="btn btn-light" onclick="callResponses()">Respond</button>
                    </div>
                </#if>
            </div>
        </div>

        <div class="modal shadow-lg" id="modal">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content bg-light border-danger">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title">Resumes</h5>
                        <button type="button" class="btn-close" onclick="closeModal();" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div id="body-modal" class="modal-body">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
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
                <#list applicantResumes as resume>
                <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                    <div class="card border-0 bg-light rounded shadow">
                        <div class="card-body p-4">
                            <h5>${resume.name}</h5>
                            <div class="mt-3">
                                <span class="text-muted d-block"></span>
                            </div>

                            <div class="row mt-3">
                                <div class="col">
                                    <button onclick="fetchToRespond('${resume.id}')">Attach</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#list>
            </div>
                `
            body.appendChild(cards);
        }

        function closeModal(cards) {
            modal.classList.remove('show')
            modal.style.display = 'none'
            body.removeChild(cards);
        }

        async function fetchToRespond(resumeId) {
            let headers = new Headers()
            if(${_csrf??}) {
                headers.set(`${_csrf.headerName}', '${_csrf.token}`)
            }

            console.log("Resume ID:", resumeId);
            await fetch('/vacancies/respond/' + ${vacancy.id} + '?resumeId=' + resumeId, {
                method: 'POST',
                headers
            })
        }

    </script>
</@main.layout>
