<#macro template currentUser>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>РСЧИР-6</title>
    </head>
    <body>
        <#if currentUser.login??>
            <p>Пользователь: ${currentUser.login}</p>
        <#else>
            <p><a href="/login">
                Войти
            </a></p>
        </#if>
        <#if currentUser.isAdmin()>
            <p><a href="/admin">Панель администратора</a> </p>
        </#if>
        <p><a href="/books">Посмотреть книги</a> </p>
        <p><a href="/telephones">Посмотреть телефоны</a> </p>
        <p><a href="/washingMachines">Посмотреть стиральные машины</a> </p>
        <#if currentUser.login??>
            <p><a href="/cart">Корзина</a> </p>
        <#else>
            <p><a href="/login">Корзина</a> </p>
        </#if>
        <#nested/>
    </body>
    </html>
</#macro>