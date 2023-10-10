<#import "../Shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h3>Books</h3>
    <table>
        <tr>
            <td>Title</td>
            <td>Author</td>
            <td>Cost</td>
            <td>SellerNumber</td>
            <td>Delete</td>
        </tr>
        <#list books as book>
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.cost}</td>
                <td>${book.sellerNumber}</td>
                <td><a href="/books/delete/${book.id}">Delete</a></td>
            </tr>
        </#list>
    </table>
    <h3>Create book</h3>
    <form action="/books/create" method="post">
        <label for="title">Title</label><input type="text" id="title">
        <label for="cost">Cost</label><input type="text" id="cost">
        <label for="sellerNumber">Seller number</label><input type="text" id="sellerNumber">
        <label for="author">Author</label><input type="text" id="author">
        <label for="count">Count</label><input type="text" id="count">
    </form>
</@layout.template>