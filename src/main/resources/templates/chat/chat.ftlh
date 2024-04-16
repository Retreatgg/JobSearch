<#import "../layot.ftlh" as main>
    <#assign script><script src="../../static/chat.js"></script></#assign>
<@main.layout script=script>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3">
                <div class="bg-light p-3">
                    <p><strong>Name:</strong> ${toUser.name}</p>
                    <p><strong>Email:</strong> ${toUser.email}</p>
                    <p><strong>Phone:</strong> ${toUser.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-9">
                <div class="bg-white border rounded p-3" style="height: 400px; overflow-y: scroll;">
                    <#list recipientMessage as message>
                        <div class="message">
                            <strong>${toUser.name}:</strong> ${message.message}
                        </div>
                    </#list>
                    <#list senderMessage as message>
                        <div class="message">
                            <strong>You:</strong> ${message.message}.
                        </div>
                    </#list>
                </div>
                <form id="form" action="/chat/send?toUser=${toUser.id}" method="post">
                    <div class="form-group">
                        <input type="hidden" name="toUserEmail" value="${toUser.email}">
                        <input class="form-control" name="message" rows="3" placeholder="Type your message..."></input>
                    </div>
                    <button id="btn" type="submit" class="btn btn-primary">Send</button>
                </form>

                <script>

                </script>
            </div>
        </div>
    </div>

</@main.layout>