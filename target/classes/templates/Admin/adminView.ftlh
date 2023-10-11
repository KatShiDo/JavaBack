<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h3>Books</h3>
    <#if books??>
        <table border="1">
            <tr>
                <td>Название</td>
                <td>Автор</td>
                <td>Стоимость</td>
                <td>Номер продавца</td>
                <td>Количество</td>
                <td>Удалить</td>
            </tr>
            <#list books as book>
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.cost}</td>
                    <td>${book.sellerNumber}</td>
                    <td>${book.count}</td>
                    <td><a href="/books/delete/${book.id}">Удалить</a></td>
                </tr>
            </#list>
        </table>
    </#if>
    <h3>Create book</h3>
    <form action="/books/create" method="post">
        <label for="title">Название </label><input type="text" id="title" name="title"><br><br>
        <label for="cost">Стоимость </label><input type="text" id="cost" name="cost"><br><br>
        <label for="sellerNumber">Номер продавца </label><input type="text" id="sellerNumber" name="sellerNumber"><br><br>
        <label for="author">Автор </label><input type="text" id="author" name="author"><br><br>
        <label for="count">Количество </label><input type="text" id="count" name="count"><br><br>
        <button type="submit">Создать</button>
    </form>
</@layout.template>