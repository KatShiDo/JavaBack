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
        <#nested/>
    </body>
    </html>
</#macro>