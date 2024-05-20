<#import "../layot.ftlh" as main>
<@main.layout "" ; spring>
    <div class="text-center">
        <h2><@spring.message "user.message.reset.password.text"/></h2>
        <p class="text-warning">${message}</p>
    </div>
</@main.layout>