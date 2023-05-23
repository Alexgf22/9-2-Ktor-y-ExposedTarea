<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create entity</h3>
        <form action="/entities" method="post">
            <p>
                <label for="value">Value:</label>
                <input type="text" name="value">
            </p>
            <p>
                <label for="name">Name:</label>
                <input type="text" name="name">
            </p>
            <p>
                <label for="sectionId">SectionId:</label>
                <input type="text" name="sectionId">
            </p>
            <p>
                <label for="order">Order:</label>
                <input type="text" name="order">
            </p>
            <p>
                <label for="description">Description:</label>
                <textarea name="description"></textarea>
            </p>

            <label for="selectArticle">Selecciona un artículo:</label>
            <!-- Aquí se añade un select para elegir un id de article -->
            <select id="selectArticle" name="articleId">
                <#list articles as article>
                    <option value="${article.id}">${article.id}</option>
                </#list>
            </select>


            <p>
                <input type="submit">
            </p>


        </form>
    </div>
</@layout.header>
