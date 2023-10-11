<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <form action="/registration" method="post">
        <label for="name">ФИО </label><input type="text" id="name" name="name"><br><br>
        <label for="login">Логин </label><input type="text" id="login" name="login"><br><br>
        <label for="email">Электронная почта </label><input type="email" id="email" name="email"><br><br>
        <label for="phoneNumber">Номер телефона </label><input type="text" id="phoneNumber"  name="phoneNumber"><br><br>
        <label for="password">Пароль </label><input type="password" id="password" name="password"><br><br>
        <button type="submit">Зарегистрироваться</button>
    </form>
</@layout.template>