<#import "../layot.ftlh" as main>
<#assign script><script src="../../static/chat.js"></script></#assign>
<@main.layout script=script ; spring>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3">
                <div class="card border-0 shadow">
                    <div class="card-body">
                        <p><strong><@spring.message "chat.name"/>:</strong> ${toUser.name}</p>
                        <p><strong><@spring.message "chat.email"/>:</strong> ${toUser.email}</p>
                        <p><strong><@spring.message "chat.phone"/>:</strong> ${toUser.phoneNumber}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div id="chat" class="card border-0 shadow mb-4" style="height: 400px; overflow-y: scroll;">
                    <div class="card-body">
                        <#list allMessages as message>
                            <#if message.toUserEmail == fromUser.email>
                                <div class="message mb-3">
                                    <div class="bg-light p-3 rounded" data-send-time="${message.sendTime}">
                                        <strong>${toUser.name}:</strong> ${message.message}
                                    </div>
                                </div>
                            <#else>
                                <div class="message mb-3">
                                    <div class="bg-primary text-white p-3 rounded" data-send-time="${message.sendTime}">
                                        <strong><@spring.message "chat.you"/>:</strong> ${message.message}
                                    </div>
                                </div>
                            </#if>
                        </#list>
                    </div>
                </div>
                <form id="form" action="/chat/send?toUser=${toUser.id}" method="post">
                    <div class="form-group mb-3">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <input type="hidden" name="toUserEmail" value="${toUser.email}">
                        <textarea class="form-control" name="message" rows="3" placeholder="$<@spring.message "chat.type.your.message"/>"></textarea>
                    </div>
                    <button id="btn" type="submit" class="btn btn-primary"><@spring.message "chat.send"/></button>
                </form>
            </div>
        </div>
    </div>

    <script>
        setInterval(function() {
            location.reload();
        }, 10000);
    </script>

</@main.layout>
