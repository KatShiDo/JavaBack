<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <p><a href="/registration">Зарегистрироваться</a></p>
    <form action="/login" method="post">
        <label for="login">Логин </label><input type="text" id="login" name="username"><br><br>
        <label for="password">Пароль </label><input type="password" id="password" name="password"><br><br>
        <button type="submit">Войти </button>
    </form>
</@layout.template>