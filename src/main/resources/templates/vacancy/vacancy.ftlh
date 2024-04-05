<#import "../layot.ftlh" as main>

<@main.layout>
    <div class="row justify-content-center mt-5">
        <div class="col-lg-8">
            <div class="card shadow mb-5">
                <div class="card-body">

                    <h5 class="card-title">${vacancy.name}</h5>
                    <p class="card-text">${vacancy.description}</p>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><strong>Salary:</strong> $${vacancy.salary} per year</li>
                        <li class="list-group-item"><strong>Experience:</strong> ${vacancy.expFrom} - ${vacancy.expTo} years</li>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</@main.layout>