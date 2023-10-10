<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <a href="/registration">Зарегистрироваться</a><br>
    <form action="/login" method="post">
        <label for="login">Login</label><input type="text" id="login" name="username"><br>
        <label for="password">Password</label><input type="password" id="password" name="password"><br>
        <button type="submit">Войти</button>
    </form>
</@layout.template>