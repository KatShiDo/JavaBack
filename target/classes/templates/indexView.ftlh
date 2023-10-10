<#import "Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <p><a href="/books">Посмотреть книги</a></p>
    <p><a href="/telephones">Посмотреть телефоны</a></p>
    <p><a href="/washingMachines">Посмотреть стиральные машины</a></p>
    <#if user.isAdmin()>
        <p><a href="/admin">Панель администратора</a> </p>
    </#if>
</@layout.template>