<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <table>
        <tr>
            <td>Title</td>
            <td>Author</td>
            <td>Cost</td>
            <td>SellerNumber</td>
            <td>Cart</td>
        </tr>
        <#list books as book>
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.cost}</td>
                <td>${book.sellerNumber}</td>
                <#if user.login??>
                    <#if user.books.get(book.id)??>
                        <td><a href="/books/cart/delete/${book.id}">Delete from cart</a> </td>
                    </#if>
                    <#else>
                        <td><a href="/books/cart/add/${book.id}">Add to cart</a></td>
                </#if>
            </tr>
        </#list>
    </table>
</@layout.template>