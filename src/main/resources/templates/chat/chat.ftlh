<#import "../layot.ftlh" as main>
<@main.layout script="">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3">
                <div class="bg-light p-3">
                    <p><strong>Name:</strong> ${employer.name}</p>
                    <p><strong>Email:</strong> ${employer.email}</p>
                    <p><strong>Phone:</strong> ${employer.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-9">
                <div class="bg-white border rounded p-3" style="height: 400px; overflow-y: scroll;">
                    <div class="message">
                        <strong>${employer.name}:</strong> Hello, how are you?
                    </div>
                    <div class="message">
                        <strong>You:</strong> Hi John! I'm doing well, thanks for asking.
                    </div>
                </div>
                <form class="mt-3">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" placeholder="Type your message..."></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Send</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</@main.layout>