<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h3>Книги</h3>
    <table border="1">
        <tr>
            <td>Название</td>
            <td>Автор</td>
            <td>Стоимость</td>
            <td>Номер продавца</td>
            <td>В корзине</td>
            <td>Добавить</td>
            <td>Убавить</td>
            <td>Удалить</td>
        </tr>
        <#list cartBooks as bookIdString, count>
            <tr>
                <#assign bookId = bookIdString?number>
                <#list books as book>
                    <#if book.id == bookId>
                        <#assign requiredBook = book>
                        <#break>
                    </#if>
                </#list>
                <td>${requiredBook.title}</td>
                <td>${requiredBook.author}</td>
                <td>${requiredBook.cost}</td>
                <td>${requiredBook.sellerNumber}</td>
                <td>${count}</td>
                <td><a href="/books/cart/increase/${requiredBook.id}">Добавить</a></td>
                <td><a href="/books/cart/decrease/${requiredBook.id}">Убавить</a> </td>
                <td><a href="/books/cart/delete/${requiredBook.id}">Удалить</a> </td>
            </tr>
        </#list>
    </table>
    <h3><a href="cart/buy">Купить всё</a> </h3>
</@layout.template>