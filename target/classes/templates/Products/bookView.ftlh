<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <table>
        <tr>
            <td>Название</td>
            <td>Автор</td>
            <td>Стоимость</td>
            <td>Номер продавца</td>
            <td>Корзина</td>
        </tr>
        <#list books as book>
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.cost}</td>
                <td>${book.sellerNumber}</td>
                <#if userAuthorized>
                    <#if userBooks??>
                        <#if userBooks["${book.id}"]??>
                            <td><a href="/books/cart/delete/${book.id}">Удалить из корзины</a> </td>
                        <#else>
                            <td><a href="/books/cart/add/${book.id}">Добавить в корзину</a></td>
                        </#if>
                    <#else>
                        <td><a href="/books/cart/add/${book.id}">Добавить в корзину</a></td>
                    </#if>
                <#else>
                    <td><a href="/login">Добавить в корзину</a> </td>
                </#if>
            </tr>
        </#list>
    </table>
</@layout.template>