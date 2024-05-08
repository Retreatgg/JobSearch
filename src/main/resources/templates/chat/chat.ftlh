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
                <div id="chat" class="bg-white border rounded p-3" style="height: 400px; overflow-y: scroll;">
                    <#list allMessages as message>
                        <#if message.toUserEmail == fromUser.email>
                            <div class="message" data-send-time="${message.sendTime}">
                                <strong>${toUser.name}:</strong> ${message.message}
                            </div>
                            <#else>
                                <div class="message" data-send-time="${message.sendTime}">
                                    <strong>You:</strong> ${message.message}.
                                </div>
                        </#if>

                    </#list>
                </div>
                <form id="form" action="/chat/send?toUser=${toUser.id}" method="post">
                    <div class="form-group">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <input type="hidden" name="toUserEmail" value="${toUser.email}">
                        <input class="form-control" name="message" rows="3" placeholder="Type your message..."></input>
                    </div>
                    <button id="btn" type="submit" class="btn btn-primary">Send</button>
                </form>
            </div>
        </div>
    </div>

</@main.layout>